package com.example.eunftel_server.repository;

import com.example.eunftel_server.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
