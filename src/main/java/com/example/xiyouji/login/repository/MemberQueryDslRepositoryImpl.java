package com.example.xiyouji.login.repository;


import com.example.xiyouji.login.entity.QMember;
import com.example.xiyouji.result_rank_comment.repository.CommentQueryDslRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.example.xiyouji.login.entity.QMember.member;

@RequiredArgsConstructor
public class MemberQueryDslRepositoryImpl implements MemberQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public boolean existUserNickName(String nickName) {
        Integer count = jpaQueryFactory
                .selectOne()
                .from(member).where(member.nickName.eq(nickName))
                .fetchFirst();
        return count != null;
    }
}
