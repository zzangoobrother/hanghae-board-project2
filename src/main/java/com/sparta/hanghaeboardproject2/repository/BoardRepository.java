package com.sparta.hanghaeboardproject2.repository;

import com.sparta.hanghaeboardproject2.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
