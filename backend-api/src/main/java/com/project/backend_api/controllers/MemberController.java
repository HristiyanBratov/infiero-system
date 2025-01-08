package com.project.backend_api.controllers;


import com.project.backend_api.dto.AuthorDTO;
import com.project.backend_api.dto.MemberDTO;
import com.project.backend_api.models.Author;
import com.project.backend_api.request.CreateAuthorRequest;
import com.project.backend_api.request.CreateMemberRequest;
import com.project.backend_api.services.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Members", description = "Endpoints for operations involving members")
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("{memberId}")
    @Operation(summary = "Searches an member by id", description = "Returns an member.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "member is in the system"),
            @ApiResponse(responseCode = "500", description = "member does not exist")})
    public MemberDTO getMemberDetails(@PathVariable("memberId") Long memberId) {
        return memberService.getMemberById(memberId);
    }

    @GetMapping()
    @Operation(summary = "Get all members", description = "Returns a list of the members")
    public List<MemberDTO> getAllMembersDetails() {
        return memberService.getAllMembers();
    }


    @GetMapping("{email}")
    @Operation(summary = "Get member by an email", description = "Returns a member with existing email")
    public ResponseEntity<String> getMemberByEmail(@RequestParam String email) {
        memberService.getMemberByEmail(email);
        return ResponseEntity.ok("Member is found successfully");
    }

    @PostMapping
    @Operation(summary = "Create a member", description = "Creates a member with the given details")
    public ResponseEntity<String> createMember(@RequestBody CreateMemberRequest createMemberRequest) {
        memberService.createMember(createMemberRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Member created successfully.");
    }

    @PutMapping
    @Operation(summary = "Update a member", description = "Updates the information about a given member")
    public ResponseEntity<String> updateMember(@RequestParam Long memberId, @RequestBody CreateMemberRequest createMemberRequest) {
        memberService.updateMember(memberId, createMemberRequest);
        return ResponseEntity.ok("Member updated successfully.");
    }

    @DeleteMapping("{memberId}")
    @Operation(summary = "Delete a member", description = "Deletes a member by the given id")
    public ResponseEntity<String> deleteMember(@PathVariable("memberId") Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok("Member deleted successfully.");
    }

}
