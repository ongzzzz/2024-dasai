package com.example.xiyouji.login.service.impl;

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
        Member member = Member.builder().nickName(nickname).build();
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }
}
