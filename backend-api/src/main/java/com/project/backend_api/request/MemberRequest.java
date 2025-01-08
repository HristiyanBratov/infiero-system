package com.project.backend_api.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberRequest {
    @Valid
    @NotNull(message = "Member first name is mandatory!")
    @NotBlank(message = "Member first name is mandatory!")
    private String firstName;
    @NotNull(message = "Member last name is mandatory!")
    @NotBlank(message = "Member last name is mandatory!")
    private String lastName;
    private String email;
    private List<Long> reservationsId;
    private LocalDate membershipDate;
    private String status;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getReservationsId() {
        return reservationsId;
    }

    public void setReservationsId(List<Long> reservationsId) {
        this.reservationsId = reservationsId;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}