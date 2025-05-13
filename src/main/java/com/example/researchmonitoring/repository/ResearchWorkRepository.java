package com.example.researchmonitoring.repository;

import com.example.researchmonitoring.model.ResearchWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResearchWorkRepository extends JpaRepository<ResearchWork, Long> {
    List<ResearchWork> findByStudentId(Long studentId);

}
