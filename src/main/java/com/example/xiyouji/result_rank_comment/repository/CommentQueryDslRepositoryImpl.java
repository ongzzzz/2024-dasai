package com.example.xiyouji.result_rank_comment.repository;

import com.example.xiyouji.result_rank_comment.entity.Comment;


import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.xiyouji.login.entity.QMember.member;
import static com.example.xiyouji.result_rank_comment.entity.QComment.comment;


@RequiredArgsConstructor
public class CommentQueryDslRepositoryImpl implements CommentQueryDslRepository {

    public final JPAQueryFactory jpaQueryFactory;
    @Override
    public Page<Comment> pagingComments(Pageable pageable) {
        List<Comment> commentList = jpaQueryFactory
                .selectFrom(comment)
                .join(comment.member, member).fetchJoin()
                .orderBy(comment.createdDate.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
        JPAQuery<Long> count = jpaQueryFactory.select(comment.count()).from(comment);
        return PageableExecutionUtils.getPage(commentList, pageable, count::fetchFirst);
    }
    @Override
    public boolean existsByMemberId(Long userId) {
        Integer exists = jpaQueryFactory
                .selectOne()
                .from(comment)
                .where(comment.member.id.eq(userId))
                .fetchFirst();
        return exists != null;
    }
}
