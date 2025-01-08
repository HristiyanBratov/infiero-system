package com.project.backend_api.services;

import com.project.backend_api.dto.MemberDTO;
import com.project.backend_api.request.MemberRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberService {
    public void createMember(MemberRequest request);
    public void updateMember(Long id , MemberRequest request);
    public MemberDTO getMemberById(Long memberId);
    public MemberDTO  getMemberByEmail(String email);
    public List<MemberDTO> getAllMembers();
    public ResponseEntity<String> deleteMember(Long memberId);
}
