package com.example.xiyouji.login.repository;


import com.example.xiyouji.login.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> , MemberQueryDslRepository{
}
