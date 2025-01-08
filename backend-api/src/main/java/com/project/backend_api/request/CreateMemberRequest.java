package com.project.backend_api.request;

import java.util.Date;
import java.util.List;

public class CreateMemberRequest {

    private String firstName;
    private String lastName;
    private String email;
    private List<Long> reservationsId;
    private Date membershipDate;

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

    public Date getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(Date membershipDate) {
        this.membershipDate = membershipDate;
    }
}
