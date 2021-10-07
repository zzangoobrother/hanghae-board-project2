package com.sparta.hanghaeboardproject2.service;

import com.sparta.hanghaeboardproject2.model.Answer;
import com.sparta.hanghaeboardproject2.model.Board;
import com.sparta.hanghaeboardproject2.repository.AnswerRepository;
import com.sparta.hanghaeboardproject2.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    private final BoardRepository boardRepository;
    private final AnswerRepository answerRepository;

    public AnswerService(BoardRepository boardRepository, AnswerRepository answerRepository) {
        this.boardRepository = boardRepository;
        this.answerRepository = answerRepository;
    }

    public List<Answer> getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("등록 된 내용이 없습니다.")
        );

        Answer answer = new Answer("test", "test", board);
        answerRepository.save(answer);

        List<Answer> answerList = answerRepository.findAllByBoard(board);
        for (Answer answer1 : answerList) {
            answer1.tranModifiedAt();;
        }
        return answerList;
    }
}
