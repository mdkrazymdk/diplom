package com.example.researchmonitoring.service;

import com.example.researchmonitoring.model.ResearchWork;
import com.example.researchmonitoring.model.User;
import com.example.researchmonitoring.model.WorkStatus;
import com.example.researchmonitoring.repository.ResearchWorkRepository;
import com.example.researchmonitoring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ResearchWorkServiceImpl implements ResearchWorkService {

    private final ResearchWorkRepository workRepo;
    private final UserRepository userRepo;

    @Override
    @Transactional(readOnly = true)
    public List<ResearchWork> findAll() {
        return workRepo.findAll();
    }

    @Override
    public ResearchWork create(ResearchWork work, String studentUsername) {
        User student = userRepo.findByUsername(studentUsername)
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + studentUsername));
        work.setStudent(student);
        work.setStatus(WorkStatus.DRAFT);
        return workRepo.save(work);
    }

    @Override
    public void updateStatus(Long id, WorkStatus newStatus) {
        ResearchWork w = workRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Work not found: " + id));
        w.setStatus(newStatus);
        workRepo.save(w);
    }
}
