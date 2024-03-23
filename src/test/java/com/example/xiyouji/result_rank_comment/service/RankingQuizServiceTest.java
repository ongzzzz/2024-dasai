package com.example.xiyouji.result_rank_comment.service;

import com.example.xiyouji.login.entity.Member;
import com.example.xiyouji.login.repository.MemberRepository;
import com.example.xiyouji.result_rank_comment.dto.RankingDto;
import com.example.xiyouji.result_rank_comment.dto.UserRankingResponse;
import com.example.xiyouji.type.Characters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest
class RankingQuizServiceTest {

    @Autowired
    private RedisTemplate<String, RankingDto> redisTemplate;
    @Autowired
    private RankingQuizService rankingQuizService;
    @Autowired
    private MemberRepository memberRepository;

    private ZSetOperations<String, RankingDto> zSetOperations;


    @BeforeEach
    void setUp() {
        RankingDto rankingDto;
        zSetOperations = redisTemplate.opsForZSet();
        rankingDto = RankingDto.of(1L , 3, "nickName" + 1, null);
        zSetOperations.add("ranking", rankingDto, 3);
        rankingDto = RankingDto.of(2L , 5, "nickName" + 2, null);
        zSetOperations.add("ranking", rankingDto, 5);
        rankingDto = RankingDto.of(3L , 1, "nickName" + 3, null);
        zSetOperations.add("ranking", rankingDto, 1);
        rankingDto = RankingDto.of(4L , 9, "nickName" + 4, null);
        zSetOperations.add("ranking", rankingDto, 9);
        rankingDto = RankingDto.of(5L , 10, "nickName" + 5, null);
        zSetOperations.add("ranking", rankingDto, 10);
        rankingDto = RankingDto.of(6L , 5, "nickName" + 6, null);
        zSetOperations.add("ranking", rankingDto, 5);
        rankingDto = RankingDto.of(7L , 7, "nickName" + 7, null);
        zSetOperations.add("ranking", rankingDto, 7);
        rankingDto = RankingDto.of(8L , 9, "nickName" + 8, null);
        zSetOperations.add("ranking", rankingDto, 9);
        rankingDto = RankingDto.of(9L , 2, "nickName" + 9, null);
        zSetOperations.add("ranking", rankingDto, 2);
        rankingDto = RankingDto.of(10L , 6, "nickName" + 9, null);
        zSetOperations.add("ranking", rankingDto, 6);
    }

    @Test
    @DisplayName("순위 저장 테스트 - 회원 랭킹이 10위 안에 존재")
    void saveRankingTest() throws Exception
    {
        List<String> characters = createCharacters();
        Member member = createMember();
        memberRepository.save(member);
        UserRankingResponse userRankingResponse = rankingQuizService.saveUserRankingAndIsUserInTopTen(1L, characters);
        assertThat(userRankingResponse).isNull();

    }
    @Test
    @DisplayName("순위 저장 테스트 - 회원 랭킹이 10위 안에 존재 X")
    void saveRankingTestCase2() throws Exception
    {
        List<String> characters = new ArrayList<>();
        Member member = createMember();
        memberRepository.save(member);
        UserRankingResponse userRankingResponse = rankingQuizService.saveUserRankingAndIsUserInTopTen(1L, characters);
        assertThat(userRankingResponse)
                .extracting("userId","rankingCount","quizResult", "nickName", "characters")
                .containsExactly(1L, 11, 10,"name", List.of(Characters.저팔계,Characters.손오공));
    }

    @Test
    @DisplayName("1위 부터 10까지 순위 검색 테스트")
    void getRankingTopTen() throws Exception {
        List<RankingDto> rankingTopTen = rankingQuizService.getRankingTopTen();
        assertThat(rankingTopTen.size()).isEqualTo(10);
        assertThat(rankingTopTen.get(0))
                .extracting("userId", "quizResult", "characters")
                .containsExactly(5L, 10, null);
        assertThat(rankingTopTen.get(1))
                .extracting("userId", "quizResult", "characters")
                .containsExactly(1L, 10, List.of(Characters.저팔계,Characters.손오공));
    }

    private Member createMember() {
        Member member = Member.builder().nickName("name").build();
        member.id = 1L;
        return member;
    }

    public List<String> createCharacters(){
        return List.of("저팔계", "저팔계", "삼장법사",
                "사오정", "저팔계", "삼장법사", "사오정", "손오공", "손오공", "손오공");
    }


}