package com.example.xiyouji.login.service.impl;

import com.example.xiyouji.exception.RestApiException;
import com.example.xiyouji.exception.impl.UserErrorCode;
import com.example.xiyouji.login.entity.Member;
import com.example.xiyouji.login.repository.MemberRepository;
import com.example.xiyouji.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final MemberRepository memberRepository;

    @Override
    public Long signup(String nickname) {
        if(memberRepository.existUserNickName(nickname)) {
            throw new RestApiException(UserErrorCode.ALREADY_EXIST_USER);
        }
        Member member = Member.builder().nickName(nickname).build();
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }
}
