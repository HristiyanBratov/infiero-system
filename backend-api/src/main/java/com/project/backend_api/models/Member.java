package com.project.backend_api.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "First_Name", nullable = false)
    private String firstName;

    @Column(name = "Last_Name", nullable = false)
    private String lastName;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "Member_Status")
    private Status memberStatus;

    @Column(name = "Membership_Date", nullable = false)
    private LocalDate membershipDate;

    @OneToMany(mappedBy = "member")
    private Set<BookLoan> bookLoans;

    @OneToMany(mappedBy = "member")
    private Set<Reservation> reservations;

    @OneToMany(mappedBy = "member")
    private Set<Fine> fines;

    public Member() {}

    public Member(String firstName, String lastName, String email, Status memberStatus, LocalDate membershipDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.memberStatus = memberStatus;
        this.membershipDate = membershipDate;

        this.bookLoans = new HashSet<>();
        this.reservations = new HashSet<>();
        this.fines = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDate getMembershipDate(Date membershipDate) {
        return this.membershipDate;
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

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Fine> getFines() {
        return fines;
    }

    public void setFines(Set<Fine> fines) {
        this.fines = fines;
    }
}