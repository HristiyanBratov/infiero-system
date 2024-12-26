package com.project.backend_api.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "library_branches")
public class LibraryBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Branch_Name", nullable = false, unique = true)
    private String branchName;

    @Column(name = "Branch_Address", nullable = false)
    private String branchAddress;

    @Column(name = "Contact_Number")
    private String contactNumber;

    @Column(name = "Opening_Hours")
    private String openingHours;

    @ManyToMany
    @JoinTable(name = "book_library_branch", joinColumns = @JoinColumn(name = "library_branch_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books;

    public LibraryBranch() {}

    public LibraryBranch(String branchName, String branchAddress, String contactNumber, String openingHours) {
        this.branchName = branchName;
        this.branchAddress = branchAddress;
        this.contactNumber = contactNumber;
        this.openingHours = openingHours;

        this.books = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}