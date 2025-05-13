package com.example.researchmonitoring.repository;

import com.example.researchmonitoring.model.Comment;
import com.example.researchmonitoring.model.ResearchWork;   // <── добавить
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByWork(ResearchWork work);
    // сигнатура совпадает с Service
}

