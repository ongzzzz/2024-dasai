package com.example.xiyouji.result_rank_comment.repository;

import com.example.xiyouji.result_rank_comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentQueryDslRepository {
    Page<Comment> pagingComments(Pageable pageable);
    boolean existsByMemberId(Long userId);
}
