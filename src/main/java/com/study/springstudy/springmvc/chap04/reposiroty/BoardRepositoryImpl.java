package com.study.springstudy.springmvc.chap04.reposiroty;

import com.study.springstudy.springmvc.chap04.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

    private final JdbcTemplate template;

    @Override
    public List<Board> findAll() {
        String sql = "select * from tbl_board";

        return template.query(sql,(rs,n)-> new Board(rs));
    }

    @Override
    public Board findOne(int boardNo) {
        String sql = "select * from tbl_board where board_no = ?";
        return template.queryForObject(sql,(rs,n)-> new Board(rs),boardNo);
    }

    @Override
    public boolean save(Board board) {
        String sql = "insert into tbl_board (title, content, writer) values (?, ?, ?)";
//        System.out.println("sql = " + sql);
        return template.update
                (sql, board.getTitle(), board.getContent(), board.getWriter()) == 1;
    }

    @Override
    public boolean delete(int boardNo) {
        String sql = "delete from tbl_board where board_no = ?";

        return template.update(sql, boardNo) == 1;

    }

    @Override
    public void upViewCount(int boardNo) {
        String sql = "UPDATE tbl_board " +
                "SET view_count = view_count + 1 " +
                "WHERE board_no = ?";
        template.update(sql, boardNo);
    }
}
