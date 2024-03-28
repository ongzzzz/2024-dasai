package com.example.xiyouji.login.repository;

import com.example.xiyouji.config.JpaConfig;
import com.example.xiyouji.login.entity.Member;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


@Import(JpaConfig.class)
@TestPropertySource(locations = "classpath:/application-test.yaml")
@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("회원 닉네임 중복검사 - 회원 닉네임 존재")
    void checkUserNickNameTest_userExist() throws Exception
    {
        //given
        Member testMember = Member.builder().nickName("test").build();
        entityManager.persist(testMember);
        //when
        boolean isTrue = memberRepository.existUserNickName("test");
        //then
        assertThat(isTrue).isTrue();
    }

    @Test
    @DisplayName("회원 닉네임 중복검사 - 회원 닉네임 존재 X")
    void checkUserNickNameTest_userNotExist() throws Exception
    {
        //given
        //when
        boolean isTrue = memberRepository.existUserNickName("test");
        //then
        assertThat(isTrue).isFalse();
    }

}