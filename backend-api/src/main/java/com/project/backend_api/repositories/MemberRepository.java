package com.project.backend_api.repositories;

import com.project.backend_api.dto.MemberDTO;
import com.project.backend_api.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional <Member> findByEmail(String email);
}
