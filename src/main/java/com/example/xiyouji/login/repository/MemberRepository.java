package com.example.xiyouji.login.repository;


import com.example.xiyouji.login.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
