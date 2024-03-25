package com.example.xiyouji.result_rank_comment.service;

import com.example.xiyouji.exception.RestApiException;
import com.example.xiyouji.exception.impl.UserErrorCode;
import com.example.xiyouji.login.entity.Member;
import com.example.xiyouji.login.repository.MemberRepository;
import com.example.xiyouji.result_rank_comment.dto.CommentResponse;
import com.example.xiyouji.result_rank_comment.entity.Comment;
import com.example.xiyouji.result_rank_comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    // 댓글 페이지 조회
    public Page<CommentResponse> getComments(Pageable pageable){
        return commentRepository.pagingComments(pageable)
                .map(data-> CommentResponse.of(data.getMember().nickName, data.getContent()));
    }

    // 댓글 저장
    @Transactional
    public void saveComment(Long userId, String content){

        if(commentRepository.existsByMemberId(userId)){
            throw new RestApiException(UserErrorCode.ALREADY_WROTE_COMMENT);
        }

        Member member = memberRepository
                .findById(userId)
                .orElseThrow(() -> new RestApiException(UserErrorCode.USER_NOT_FOUND));
        Comment comment = Comment.builder().content(content).member(member).build();

        commentRepository.save(comment);
    }

}
