package com.study.springstudy.springmvc.chap04.controller;


import com.study.springstudy.springmvc.chap04.dto.BoardWriteRequestDto;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.reposiroty.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
   private final BoardRepository repository;

    // 1. 목록 조회 요청 (/board/list : GET)
    @GetMapping("/list")
    public String list (Model model) {
        List<Board> boardList = repository.findAll();
        model.addAttribute("boardList", boardList);

        return "/board/list";
    }


    // 2. 글 쓰기 양식 화면 열기 요청 (/board/write : GET)
    @GetMapping("/write")
    public String write () {

        return "/board/write";
    }

    // 3. 게시글 등록 요청 (/board/write : POST)
    // -> 목록조회 요청 리다이렉션
    @PostMapping("/write")
    // 브라우저가 전달한 게시글 내용 읽기
    public String write (BoardWriteRequestDto dto) {
//        Board newBoard = new Board();
//        model.addAttribute("board", newBoard);

        Board b = dto.toEntity();
        repository.save(b);
        return "redirect:/board/list";

    }

    // 4. 게시글 삭제 요청 (/board/delete : GET)
    // -> 목록조회 요청 리다이렉션
    @GetMapping("/delete")
    public String delete (int bno) {
        repository.delete(bno);
                return "redirect:/board/list";
    }

    // 5. 게시글 상세 조회 요청 (/board/detail : GET)

    @GetMapping
    public String detail (int bno, Model model) {
        model.addAttribute("bno", bno);
        repository.findOne(bno);

        return "/board/detail";
    }


}
