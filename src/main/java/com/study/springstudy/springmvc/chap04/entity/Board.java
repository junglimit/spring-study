package com.study.springstudy.springmvc.chap04.entity;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Setter @Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class Board {

    private int boardNo; // 게시글 번호
    private String title; // 글제목
    private String content; // 글내용
    private String writer; // 작성자명
    private int viewCount; // 조회수
    private LocalDateTime regDateTime; // 작성일시

    public Board(ResultSet rs) throws SQLException {
        this.title = rs.getString("title");
        this.content = rs.getString("content");
        this.writer = rs.getString("writer");
        this.regDateTime = rs.getTimestamp("reg_date_time").toLocalDateTime();
        this.viewCount = rs.getInt("view_count");
        this.boardNo = rs.getInt("board_no");
    }
}
