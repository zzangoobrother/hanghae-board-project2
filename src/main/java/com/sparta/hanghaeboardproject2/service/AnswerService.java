package com.sparta.hanghaeboardproject2.service;

import com.sparta.hanghaeboardproject2.dto.AnswerDto;
import com.sparta.hanghaeboardproject2.model.Answer;
import com.sparta.hanghaeboardproject2.model.Board;
import com.sparta.hanghaeboardproject2.repository.AnswerRepository;
import com.sparta.hanghaeboardproject2.repository.BoardRepository;
import com.sparta.hanghaeboardproject2.security.MemberDetailsImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

        List<Answer> answerList = answerRepository.findAllByBoardOrderByModifiedAtDesc(board);
        for (Answer answer1 : answerList) {
            answer1.tranModifiedAt();;
        }
        return answerList;
    }

    public Long createAnswer(AnswerDto answerDto, MemberDetailsImpl memberDetails) {
        Board board = boardRepository.findById(answerDto.getBoardId()).orElseThrow(
                () -> new IllegalArgumentException("해당 게시판이 존재 하지 않습니다.")
        );
        Answer answer = new Answer(memberDetails.getUsername(), answerDto.getContents(), board);
        Answer saveAnswer = answerRepository.save(answer);

        return saveAnswer.getId();
    }

    @Transactional
    public Long RequestBody(AnswerDto answerDto, MemberDetailsImpl memberDetails) {
        Answer answer = answerRepository.findById(answerDto.getAnswerId()).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );

        answer.update(answerDto);
        return answer.getId();
    }

    public void deleteAnswer(AnswerDto answerDto, MemberDetailsImpl memberDetails) {
        answerRepository.deleteById(answerDto.getAnswerId());
        return;
    }
}
