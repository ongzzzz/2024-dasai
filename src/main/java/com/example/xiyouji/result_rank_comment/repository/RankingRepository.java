package com.example.xiyouji.result_rank_comment.repository;

import com.example.xiyouji.result_rank_comment.entity.Ranking;
import org.hibernate.dialect.MySQLDialect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RankingRepository extends CrudRepository<Ranking, Long> {

//    @Query("select r from Ranking r join fetch r.member " +
//            "order by r.createdDate desc , r.resultCount desc limit 10")
//    List<Ranking> findByTenRankings();

}
