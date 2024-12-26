package com.project.backend_api.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "First_Name", nullable = false)
    private String firstName;

    @Column(name = "Last_Name", nullable = false)
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "Member_Status")
    private Status memberStatus;

    @Column(name = "Membership_Date", nullable = false)
    private LocalDate membershipDate;

    @OneToMany(mappedBy = "member")
    private Set<BookLoan> bookLoans;

    public Member() {}

    public Member(String firstName, String lastName, String email, Status memberStatus, LocalDate membershipDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.memberStatus = memberStatus;
        this.membershipDate = membershipDate;

        this.bookLoans = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Status getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(Status memberStatus) {
        this.memberStatus = memberStatus;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }

    public Set<BookLoan> getBookLoans() {
        return bookLoans;
    }

    public void setBookLoans(Set<BookLoan> bookLoans) {
        this.bookLoans = bookLoans;
    }
}