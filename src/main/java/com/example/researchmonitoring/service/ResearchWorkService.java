package com.example.researchmonitoring.service;

import com.example.researchmonitoring.model.ResearchWork;
import com.example.researchmonitoring.model.WorkStatus;

import java.util.List;

public interface ResearchWorkService {

    /**
     * Получить все научные работы.
     */
    List<ResearchWork> findAll();

    /**
     * Создать новую работу и привязать её к студенту (username берём из JWT).
     */
    ResearchWork create(ResearchWork work, String studentUsername);

    /**
     * Обновить статус работы.
     */
    void updateStatus(Long id, WorkStatus newStatus);
}
