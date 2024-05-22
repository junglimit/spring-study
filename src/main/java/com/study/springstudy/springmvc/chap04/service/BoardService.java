package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.common.Page;
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




    public List<BoardListResponseDto> findList(Page page) {
        List<Board> bList = mapper.findAll(page);

        // 조회해온 게시물 리스트에서 각 게시물들의 조회수를 확인하여
        // 조회수가 5 이상인 게시물에 특정 마킹


        List<BoardListResponseDto> dtoList = bList.stream()
                .map(b -> new BoardListResponseDto(b))
                .collect(Collectors.toList());
        return dtoList;

    }
    public boolean insert(BoardWriteRequestDto dto){
        Board b = dto.toEntity();
        return mapper.save(b);
    }


    public BoardDetailResponseDto detail(int bno) {
        Board b = mapper.findOne(bno);
        if(b != null) mapper.upViewCount(bno);

        return new BoardDetailResponseDto(b);

    }


    public boolean save(Board board) {
        return mapper.save(board);

    }


    public boolean remove(int boardNo) {
        return mapper.delete(boardNo);

    }


    public void upViewCount(int boardNo) {
        mapper.upViewCount(boardNo);
    }


}
