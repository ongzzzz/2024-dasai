package com.example.xiyouji.login.service.impl;

import com.example.xiyouji.login.entity.Member;
import com.example.xiyouji.login.repository.MemberRepository;
import com.example.xiyouji.login.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private UserServiceImpl userService;


    @Test
    @DisplayName("회원 닉네임 저장 및 회원 ID 반환 테스트")
    void signupTest() throws Exception
    {
        //given
        Member member = Member.builder().nickName("testName").build();
        member.id = 1L;
        given(memberRepository.save(any())).willReturn(member);
        //when
        Long userId = userService.signup("testName");
        //then
        assertThat(userId).isEqualTo(1L);
        then(memberRepository).should().save(any());
    }

}