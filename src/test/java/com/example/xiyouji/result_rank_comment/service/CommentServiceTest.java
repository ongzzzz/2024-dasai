package com.example.xiyouji.result_rank_comment.service;

import com.example.xiyouji.login.entity.Member;
import com.example.xiyouji.login.repository.MemberRepository;
import com.example.xiyouji.result_rank_comment.constant.CustomException;
import com.example.xiyouji.result_rank_comment.constant.ErrorCode;
import com.example.xiyouji.result_rank_comment.entity.Comment;
import com.example.xiyouji.result_rank_comment.repository.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchRuntimeException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private CommentService commentService;


    @Test
    @DisplayName("comment 댓글 조회 테스트")
    void getCommentTest() throws Exception
    {
        //given
        List<Comment> comments = createComments();
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("createdDate").descending());
        given(commentRepository.pagingComments(any())).willReturn(new PageImpl<>(comments));
        //when
        Page<CommentsResponse> commentsPage = commentService.getComments(pageRequest);
        //then
        assertThat(commentsPage.isFirst()).isTrue();
        assertThat(commentsPage.getNumberOfElements()).isEqualTo(5);
        assertThat(commentsPage.getNumber()).isEqualTo(0);

        for (int i = 0; i < 5; i++) {
            assertThat(commentsPage.getContent().get(i))
                    .extracting("nickName", "content")
                    .containsExactly("name"+(20-i), "content" + (20-i));
        }

        then(commentRepository).should().pagingComments(any());
    }
    
    @Test
    @DisplayName("회원 저장 테스트 성공")
    void saveMemberSuccessTest() throws Exception
    {
        //given
        Member member = Member.builder().nickName("name").build();
        given(commentRepository.existsByMemberId(any())).willReturn(false);
        given(memberRepository.findById(any())).willReturn(Optional.of(member));
        //when
        commentService.saveComment(1L, "content");
        //then
        then(commentRepository).should().existsByMemberId(any());
        then(memberRepository).should().findById(any());
    }
    @Test
    @DisplayName("회원 저장 테스트 실패 - 이미 작성된 회원 존재")
    void saveMemberFailureTest() throws Exception
    {
        //given
        Member member = Member.builder().nickName("name").build();
        given(commentRepository.existsByMemberId(any())).willReturn(true);
        //when
        CustomException exception =
                (CustomException) catchRuntimeException(() -> commentService.saveComment(1L, "content"));
        //then
        assertThat(exception).hasMessage(ErrorCode.ALREADY_WROTE_COMMENT.getMessage());
        then(commentRepository).should().existsByMemberId(any());
    }

    private List<Comment> createComments() {
        
        Member member;
        List<Comment> list = new ArrayList<>();
        for (int i = 20; i > 15 ; i--) {
            member = Member.builder().nickName("name" + i).build();
            list.add(Comment.builder().member(member).content("content" + i).build());
        }
        return list;
    }
}