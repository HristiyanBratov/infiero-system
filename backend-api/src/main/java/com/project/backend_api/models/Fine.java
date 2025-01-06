package com.project.backend_api.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "fines")
public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Amount", nullable = false)
    private Double amount;

    @Column(name = "Due_Date", nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Fine_Status", nullable = false)
    private Status fineStatus;

    /*@ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;*/

    @OneToOne
    @JoinColumn(name = "book_load_id", nullable = false, unique = true)
    private BookLoan bookLoan;

    public Fine() {}

    public Fine(Double amount, LocalDate dueDate, Status fineStatus, /*Member member,*/ BookLoan bookLoan) {
        this.amount = amount;
        this.dueDate = dueDate;
        this.fineStatus = fineStatus;
        //this.member = member;
        this.bookLoan = bookLoan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Status getFineStatus() {
        return fineStatus;
    }

    public void setFineStatus(Status fineStatus) {
        this.fineStatus = fineStatus;
    }

    /*public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }*/

    public BookLoan getBookLoan() {
        return bookLoan;
    }

    public void setBookLoan(BookLoan bookLoan) {
        this.bookLoan = bookLoan;
    }
}