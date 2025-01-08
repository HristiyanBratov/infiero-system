package com.project.backend_api.services;

import com.project.backend_api.dto.BookDTO;
import com.project.backend_api.dto.MemberDTO;
import com.project.backend_api.request.CreateBookRequest;
import com.project.backend_api.request.CreateMemberRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    public void createMember(CreateMemberRequest request);
    public void updateMember(Long id ,CreateMemberRequest request);
    public ResponseEntity<String> deleteMember(Long memberId);
    public MemberDTO getMemberById(Long memberId);
    public Optional<MemberDTO>  getMemberByEmail(String email);
    public List<MemberDTO> getAllMembers();

}
