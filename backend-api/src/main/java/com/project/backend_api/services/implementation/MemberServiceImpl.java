package com.project.backend_api.services.implementation;

import com.project.backend_api.dto.MemberDTO;
import com.project.backend_api.mappers.MemberDTOMapper;
import com.project.backend_api.models.*;
import com.project.backend_api.repositories.*;
import com.project.backend_api.request.CreateMemberRequest;
import com.project.backend_api.services.MemberService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Email;
import org.hibernate.query.results.implicit.ImplicitFetchBuilderDiscriminatedAssociation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    public void createMember(CreateMemberRequest request) {
        Set<Reservation> reservations = reservationRepository.findAllById(request.getReservationsId()).stream().collect(Collectors.toSet());

        if (reservations.isEmpty()) {
            throw new IllegalArgumentException("At least one reservaion is required");
        }

        Member member = new Member();
        member.setFirstName(request.getFirstName());
        member.setLastName(request.getLastName());
        member.setEmail(request.getEmail());
        member.setReservations(reservations);
        member.getMembershipDate(request.getMembershipDate());


        memberRepository.save(member);

    }

    @Override
    public void updateMember(Long memberId, CreateMemberRequest request) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member with ID " + memberId + " not found"));

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
    public ResponseEntity<String> deleteMember(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member is not found to be deleted.");
        }
        memberRepository.deleteById(memberId);
        return ResponseEntity.ok("Member deleted successfully.");
    }

    @Override
    public MemberDTO getMemberById(Long memberId) {
        return memberRepository.findById(memberId).map(memberDTOMapper).orElseThrow(() -> new EntityNotFoundException("Member with id: " + memberId + " not found."));
    }

    @Override
    public Optional<MemberDTO> getMemberByEmail(String email) {
        try {
            return memberRepository.findByEmail(email).map(memberDTOMapper);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Member with email: " + email + " not found.");
        }

    }

    @Override
    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll().stream().map(memberDTOMapper).collect(Collectors.toList());
    }
}
