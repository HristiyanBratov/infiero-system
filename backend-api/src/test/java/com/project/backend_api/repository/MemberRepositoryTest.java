package com.project.backend_api.repository;

import com.project.backend_api.models.Member;
import com.project.backend_api.models.Status;
import com.project.backend_api.repositories.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    Member member;


    @BeforeEach
    void setUp() {
        member = new Member("Jo", "Hawk", "jo_h@gmail.com", Status.ACTIVE, LocalDate.parse("1961-10-11"));
        memberRepository.save(member);
    }

    @AfterEach
    void tearDown() {
        member = null;
        memberRepository.deleteAll();
    }


    @Test
    void testFindByEmail_Found() {
        List<Member> membersList = memberRepository.findMemberByEmail("jo_h@gmail.com").stream().toList();
        assertThat(membersList.get(0).getId()).isEqualTo(member.getId());
        assertThat(membersList.get(0).getFirstName()).isEqualTo(member.getFirstName());
        assertThat(membersList.get(0).getLastName()).isEqualTo(member.getLastName());
        assertThat(membersList.get(0).getMemberStatus()).isEqualTo(member.getMemberStatus());
    }


    @Test
    void testFindByEmail_NotFound() {
        List<Member> membersList = memberRepository.findMemberByEmail("john_h@gmail.com").stream().toList();
        assertThat(membersList.isEmpty()).isTrue();
    }

}

