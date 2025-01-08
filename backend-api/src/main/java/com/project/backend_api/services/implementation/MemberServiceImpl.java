package com.project.backend_api.services.implementation;

import com.project.backend_api.dto.MemberDTO;
import com.project.backend_api.mappers.MemberDTOMapper;
import com.project.backend_api.models.*;
import com.project.backend_api.repositories.*;
import com.project.backend_api.request.MemberRequest;
import com.project.backend_api.services.MemberService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberDTOMapper memberDTOMapper;
    private final ReservationRepository reservationRepository;

    private MemberServiceImpl(MemberRepository memberRepository, MemberDTOMapper memberDTOMapper, ReservationRepository reservationRepository) {
        this.memberRepository = memberRepository;
        this.memberDTOMapper = memberDTOMapper;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void createMember(MemberRequest request) {
        Set<Reservation> reservations = new HashSet<>(reservationRepository.findAllById(request.getReservationsId()));
        Status status = Status.valueOf(request.getStatus());

        if (reservations.isEmpty()) {
            throw new IllegalArgumentException("At least one reservaion is required");
        }

        Member member = new Member();
        member.setFirstName(request.getFirstName());
        member.setLastName(request.getLastName());
        member.setEmail(request.getEmail());
        member.setReservations(reservations);
        member.setMembershipDate(request.getMembershipDate());
        member.setMemberStatus(status);

        memberRepository.save(member);
    }

    @Override
    public void updateMember(Long memberId, MemberRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member with ID " + memberId + " not found"));

        if (request.getFirstName() != null) {
            member.setFirstName(request.getFirstName());
        }

        if (request.getLastName() != null) {
            member.setLastName(request.getLastName());
        }

        if (request.getEmail() != null) {
            member.setEmail(request.getEmail());
        }

        memberRepository.save(member);
    }

    @Override
    public MemberDTO getMemberById(Long memberId) {
        return memberRepository.findById(memberId).map(memberDTOMapper)
                .orElseThrow(() -> new EntityNotFoundException("Member with id: " + memberId + " not found."));
    }

    @Override
    public MemberDTO getMemberByEmail(String email) {
        return memberRepository.findMemberByEmail(email).map(memberDTOMapper)
                .orElseThrow(() -> new EntityNotFoundException("Member with email: " + email + " not found."));
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll().stream().map(memberDTOMapper).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> deleteMember(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member is not found to be deleted.");
        }
        memberRepository.deleteById(memberId);
        return ResponseEntity.ok("Member deleted successfully.");
    }
}