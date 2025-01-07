package com.project.backend_api.repositories;

import com.project.backend_api.models.LibraryBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryBranchRepository extends JpaRepository<LibraryBranch, Long> {
    Optional<LibraryBranch> findByBranchName(String branchName);
}
