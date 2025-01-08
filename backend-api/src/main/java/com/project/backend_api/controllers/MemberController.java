package com.project.backend_api.controllers;

import com.project.backend_api.dto.MemberDTO;
import com.project.backend_api.request.MemberRequest;
import com.project.backend_api.services.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Members", description = "Endpoints for operations involving members")
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    @Operation(summary = "Create a member", description = "Creates a member with the given details")
    public ResponseEntity<String> createMember(@Valid @RequestBody MemberRequest request) {
        memberService.createMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Member created successfully.");
    }

    @PutMapping
    @Operation(summary = "Update a member", description = "Updates the information about a given member")
    public ResponseEntity<String> updateMember(@RequestParam Long memberId, @RequestBody MemberRequest memberRequest) {
        memberService.updateMember(memberId, memberRequest);
        return ResponseEntity.ok("Member updated successfully.");
    }

    @GetMapping("email")
    @Operation(summary = "Get member by an email", description = "Returns a member with existing email")
    public MemberDTO getMemberByEmail(@RequestParam String email) {
        return memberService.getMemberByEmail(email);
    }

    @GetMapping("id/{memberId}")
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

    @DeleteMapping("{memberId}")
    @Operation(summary = "Delete a member", description = "Deletes a member by the given id")
    public ResponseEntity<String> deleteMember(@PathVariable("memberId") Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok("Member deleted successfully.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}