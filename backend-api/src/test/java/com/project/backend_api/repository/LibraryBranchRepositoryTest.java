package com.project.backend_api.repository;


import com.project.backend_api.models.LibraryBranch;
import com.project.backend_api.repositories.LibraryBranchRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class LibraryBranchRepositoryTest {
    @Autowired
    private LibraryBranchRepository libraryBranchRepository;
    private LibraryBranch libraryBranch;

    @BeforeEach
    public void setUp() {
        libraryBranch = new LibraryBranch("Sofia_Branch", "Sofia", "0884543231", "10AM:18PM");
        libraryBranchRepository.save(libraryBranch);
    }

    @AfterEach
    public void tearDown() {
        libraryBranch = null;
        libraryBranchRepository.deleteAll();
    }


    //Success case
    @Test
    void findByBranchName_Found() {
        List<LibraryBranch> libraryBranches = libraryBranchRepository
                .findByBranchName("Sofia_Branch")
                .stream()
                .toList();
        assertThat(libraryBranches.get(0).getBranchName()).isEqualTo("Sofia_Branch");
        assertThat(libraryBranches.get(0).getBranchName())
                .isEqualTo(libraryBranch.getBranchName());
    }


    @Test
    void findByBranchName_NotFound() {
        List<LibraryBranch> libraryBranches = libraryBranchRepository
                .findByBranchName("Plovdiv")
                .stream()
                .toList();
        assertThat(libraryBranches.isEmpty()).isTrue();


    }

}
