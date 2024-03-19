package com.example.xiyouji.result_rank_comment.repository;

import com.example.xiyouji.result_rank_comment.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RankRepository extends JpaRepository<Rank, Long> {

    @Query("select r from Rank r join fetch r.member " +
            "order by r.createdDate desc , r.resultCount desc limit 10")
    List<Rank> findByTenRanks();

}
