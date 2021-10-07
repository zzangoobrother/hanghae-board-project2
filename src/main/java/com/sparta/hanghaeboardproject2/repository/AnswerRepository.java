package com.sparta.hanghaeboardproject2.repository;

import com.sparta.hanghaeboardproject2.model.Answer;
import com.sparta.hanghaeboardproject2.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByBoard(Board board);
}
