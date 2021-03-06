package com.sparta.hanghaeboardproject2.model;

import com.sparta.hanghaeboardproject2.dto.AnswerDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Answer extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID", nullable = false)
    private Board board;

    public Answer(String writer, String contents, Board board) {
        this.writer = writer;
        this.contents = contents;
        this.board = board;
    }

    public Answer(AnswerDto answerDto, String username) {
        this.writer = username;
        this.contents = answerDto.getContents();
    }

    public void update(AnswerDto answerDto) {
        this.contents = answerDto.getContents();
    }
}
