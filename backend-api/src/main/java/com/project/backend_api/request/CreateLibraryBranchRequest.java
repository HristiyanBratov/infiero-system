package com.project.backend_api.request;

import java.util.Set;

public class CreateLibraryBranchRequest {
    private String branchName;
    private String branchAddress;
    private String branchNunber;
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
