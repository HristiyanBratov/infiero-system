package com.project.backend_api.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class LibraryBranchRequest {
    @Valid
    @NotNull(message = "Library branch name is mandatory!")
    @NotBlank(message = "Library branch name is mandatory!")
    private String branchName;
    @NotNull(message = "Library branch address is mandatory!")
    @NotBlank(message = "Library branch address is mandatory!")
    private String branchAddress;
    @NotNull(message = "Library branch number is mandatory!")
    @NotBlank(message = "Library branch number is mandatory!")
    private String branchNunber;
    @NotNull(message = "Library branch opening hours is mandatory!")
    @NotBlank(message = "Library branch opening hours is mandatory!")
    private String openingHours;
    private Set<Long> bookIds;


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

    public String getBranchNunber() {
        return branchNunber;
    }

    public void setBranchNunber(String branchNunber) {
        this.branchNunber = branchNunber;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public Set<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(Set<Long> bookIds) {
        this.bookIds = bookIds;
    }
}