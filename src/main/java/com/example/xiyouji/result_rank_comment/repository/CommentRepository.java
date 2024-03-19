package com.example.xiyouji.result_rank_comment.repository;

import com.example.xiyouji.result_rank_comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    //Page<Comment> findAllBy(Pageable pageable);
}
