package com.example.xiyouji.login.service.impl;

import com.example.xiyouji.exception.RestApiException;
import com.example.xiyouji.exception.impl.UserErrorCode;
import com.example.xiyouji.login.entity.Member;
import com.example.xiyouji.login.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchRuntimeException;
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
    @DisplayName("회원 닉네임 저장 및 회원 ID 반환 테스트(존재하지 않은 닉네임)")
    void signupTest_userNickNameNotExist() throws Exception
    {
        //given
        String nickName = "testName";
        Member member = Member.builder().nickName(nickName).build();
        member.id = 1L;
        given(memberRepository.existUserNickName(any())).willReturn(false);
        given(memberRepository.save(any())).willReturn(member);
        //when
        Long userId = userService.signup(nickName);
        //then
        assertThat(userId).isEqualTo(1L);
        then(memberRepository).should().existUserNickName(any());
        then(memberRepository).should().save(any());
    }

    @Test
    @DisplayName("회원 닉네임 중복 검사 - 회원 닉네임 존재")
    void signupTest_userNickNameExist() throws Exception
    {
        //given
        given(memberRepository.existUserNickName(any())).willReturn(true);
        //when
        RestApiException exception = (RestApiException) catchRuntimeException(() -> userService.signup("test"));

        //then
        assertThat(exception).hasMessage(UserErrorCode.ALREADY_EXIST_USER.getMessage());
        then(memberRepository).should().existUserNickName(any());
    }


}