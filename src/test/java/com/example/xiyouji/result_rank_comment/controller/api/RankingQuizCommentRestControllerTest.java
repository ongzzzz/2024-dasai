package com.example.xiyouji.result_rank_comment.controller.api;

import com.example.xiyouji.result_rank_comment.dto.CommentRequest;
import com.example.xiyouji.result_rank_comment.dto.CommentResponse;
import com.example.xiyouji.result_rank_comment.dto.UserRankingResponse;
import com.example.xiyouji.result_rank_comment.service.CommentService;
import com.example.xiyouji.result_rank_comment.service.RankingQuizService;
import com.example.xiyouji.translate.service.impl.BaiduTranslator;
import com.example.xiyouji.type.Characters;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.java.accessibility.util.Translator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RankingQuizCommentRestController.class)
class RankingQuizCommentRestControllerTest {

    @MockBean
    private RankingQuizService rankingQuizService;
    @MockBean
    private CommentService commentService;

    @MockBean
    private BaiduTranslator translator;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mvc;


    @Test
    @DisplayName("[API[POST] 랭킹, 퀴즈 결과, 코멘트 조회 및 회원 퀴즈 결과 저장 테스트")
    void GetRankingQuizResultsCommentsAndSaveMemberQuizResultsTest() throws Exception
    {
        //given
        List<String> characters = List.of("손오공", "손오공", "손오공", "사오정", "저팔계");
        UserRankingResponse userRankingResponse = createUserRankingResponse();
        given(rankingQuizService.saveUserRankingAndIsUserInTopFive(any(), any())).willReturn(userRankingResponse);
        //when & then
        mvc.perform(post("/quiz/result/" + 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(characters))
        ).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userRanking.userId").value(1L))
                .andExpect(jsonPath("$.userRanking.rankingCount").value(5))
                .andExpect(jsonPath("$.userRanking.quizResult").value(5))
                .andExpect(jsonPath("$.userRanking.nickName").value("name"))
                .andExpect(jsonPath("$.userRanking.characters[0]").value("손오공"));

        then(rankingQuizService).should().saveUserRankingAndIsUserInTopFive(any(), any());
    }

    @Test
    @DisplayName("[API][POST] 댓글 저장 및 조회 테스트")
    void saveCommentAndGetCommentsTest() throws Exception
    {
        //given
        CommentRequest commentRequest = CommentRequest.of("content1");
        List<CommentResponse> commentResponses = createComments();
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("createdDate").descending());
        willDoNothing().given(commentService).saveComment(any(), any());
        given(commentService.getComments(any())).willReturn(new PageImpl<>(commentResponses,pageRequest,  5));
        //when $ then
        mvc.perform(post("/comment/" + 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentRequest))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].nickName").value("nickName1"))
                .andExpect(jsonPath("$.content[0].content").value("content1"));


        then(commentService).should().saveComment(any(), any());
        then(commentService).should().getComments(any());
    }

    @Test
    @DisplayName("[API][GET] 페이징 코멘트 테스트")
    void getPagingCommentTest() throws Exception
    {
        //given
        List<CommentResponse> commentResponses = createComments();
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("createdDate").descending());
        given(commentService.getComments(any())).willReturn(new PageImpl<>(commentResponses, pageRequest, 5));
        //when & then
        mvc.perform(get("/comment")
                .queryParam("page", "0")
                .queryParam("size", "5")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].nickName").value("nickName1"))
                .andExpect(jsonPath("$.content[0].content").value("content1"));


        then(commentService).should().getComments(any());
    }

    @Test
    @DisplayName("댓글 번역 테스트 - 중국어 -> 한국어")
    void commentTranslateTest() throws Exception
    {
        //given
        CommentRequest commentRequest = CommentRequest.of("你好");
        given(translator.translate(any(), any(), any())).willReturn("안녕하세요");
        //when & then
        mvc.perform(get("/comment/translate/" + "cn")
                .content(objectMapper.writeValueAsString(commentRequest))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content").value("안녕하세요"));

        then(translator).should().translate(any(), any(), any());
    }



    private List<CommentResponse> createComments() {
        List<CommentResponse> list = new ArrayList<>();
        CommentResponse commentResponse;
        for (int i = 1; i <= 5; i++) {
            commentResponse = CommentResponse.of("nickName" + i, "content" + i);
            list.add(commentResponse);
        }
        return list;
    }

    private UserRankingResponse createUserRankingResponse() {
        return UserRankingResponse.of(
                1L, 5L, 5, "name", List.of(Characters.손오공)
        );
    }


}