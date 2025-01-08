package com.project.backend_api.repositories;

import com.project.backend_api.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT mb FROM Member mb WHERE mb.email = :email")
    Optional <Member> findMemberByEmail(@Param("email") String email);
}