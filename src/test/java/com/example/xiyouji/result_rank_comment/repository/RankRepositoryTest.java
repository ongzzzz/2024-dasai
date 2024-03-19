package com.example.xiyouji.result_rank_comment.repository;

import com.example.xiyouji.login.entity.Member;
import com.example.xiyouji.result_rank_comment.entity.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@TestPropertySource(locations = "classpath:application-test.yaml")
@DataJpaTest
class RankRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private RankRepository rankRepository;

    @Test
    @DisplayName("10개 순위 추출 테스트 - 최근 저장된 순위 + 정답 결과 높은 순 정렬")
    void findByTenRanksTest() throws Exception
    {
        //given
        createRank();
        //when
        List<Rank> byTenRanks = rankRepository.findByTenRanks();

        //then
        assertThat(byTenRanks.size()).isEqualTo(10);
        for (int i = 0; i < 10; i++) {
            assertThat(byTenRanks.get(i).getMember().getNickName()).isEqualTo("nickName" + (20-i));
            assertThat(byTenRanks.get(i).getResultCount()).isEqualTo((20-i));
        }
    }

    private void createRank() {
        Rank rank;
        Member member;
        for (int i = 0; i < 20; i++) {
            member = Member.builder().nickName("nickName" + (i+1)).build();
            entityManager.persist(member);
            rank = Rank.builder().member(member).resultCount(i+1).build();
            entityManager.persist(rank);
        }
    }

}