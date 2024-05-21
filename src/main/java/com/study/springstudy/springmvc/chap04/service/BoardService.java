package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardWriteRequestDto;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper mapper;




    public List<BoardListResponseDto> findAll() {
        List<Board> bList = mapper.findAll();
        return   bList.stream()
                .map(b -> new BoardListResponseDto(b))
                .collect(Collectors.toList());

    }
    public boolean save(BoardWriteRequestDto dto){
        Board b = dto.toEntity();
        return mapper.save(b);
    }


    public BoardDetailResponseDto findOne(int boardNo) {

        return new BoardDetailResponseDto(mapper.findOne(boardNo));

    }


    public boolean save(Board board) {
        return mapper.save(board);

    }


    public boolean delete(int boardNo) {
        return mapper.delete(boardNo);

    }


    public void upViewCount(int boardNo) {
        mapper.upViewCount(boardNo);
    }


}
