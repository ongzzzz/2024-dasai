package com.example.xiyouji.testData;

import com.example.xiyouji.login.entity.Member;
import com.example.xiyouji.login.repository.MemberRepository;
import com.example.xiyouji.result_rank_comment.dto.RankingDto;
import com.example.xiyouji.result_rank_comment.entity.Comment;
import com.example.xiyouji.result_rank_comment.repository.CommentRepository;
import com.example.xiyouji.type.Characters;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TestData {

    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final RedisTemplate<String, RankingDto> redisTemplate;

    @PostConstruct
    public void init(){
        ZSetOperations<String, RankingDto> zSetOperations = redisTemplate.opsForZSet();
        Member member;
        for (int i = 1; i <= 10; i++) {
            member = Member.builder().nickName("name" + i).build();
            Member savedMember = memberRepository.save(member);

            RankingDto rankingDto =
                    RankingDto.of(savedMember.getId(), i, member.getNickName(), List.of(Characters.손오공));
            zSetOperations.add("ranking", rankingDto, i);

            Comment comment = Comment.builder().content("content" + i).member(member).build();
            commentRepository.save(comment);
        }
        member = Member.builder().nickName("name" + 11).build();
        memberRepository.save(member);
    }

}
