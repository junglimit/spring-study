package com.study.springstudy.springmvc.chap04.controller;


import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap04.common.PageMaker;
import com.study.springstudy.springmvc.chap04.common.Search;
import com.study.springstudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardWriteRequestDto;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.reposiroty.BoardRepository;
import com.study.springstudy.springmvc.chap04.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
//    private final BoardRepository repository;
    private final BoardService service;
    // 1. 목록 조회 요청 (/board/list : GET)
    @GetMapping("/list")
    public String list(@ModelAttribute("s") Search page, Model model) {
//        System.out.println("page = " + page.getPageNo());
        // 데이터 베이스에서 목록을 조회
        List<BoardListResponseDto> bList = service.findList(page);
        // 페이지 정보를 생성하여 JSP 에게 전송
        PageMaker maker = new PageMaker(page, service.getCount(page));


        // jsp 파일에 데이터 전달
        model.addAttribute("bList", bList);
        model.addAttribute("maker", maker);
//        model.addAttribute("s", page);

        return "/board/list";
    }


    // 2. 글 쓰기 양식 화면 열기 요청 (/board/write : GET)
    @GetMapping("/write")
    public String write() {

        return "/board/write";
    }

    // 3. 게시글 등록 요청 (/board/write : POST)
    // -> 목록조회 요청 리다이렉션
    @PostMapping("/write")
    // 브라우저가 전달한 게시글 내용 읽기
    public String write(BoardWriteRequestDto dto) {
//        Board newBoard = new Board();
//        model.addAttribute("board", newBoard);

        service.insert(dto);
        return "redirect:/board/list";

    }

    // 4. 게시글 삭제 요청 (/board/delete : GET)
    // -> 목록조회 요청 리다이렉션
    @GetMapping("/delete")
    public String delete(int bno) {
        service.remove(bno);
        return "redirect:/board/list";
    }

    // 5. 게시글 상세 조회 요청 (/board/detail : GET)

    @GetMapping("/detail")
    public String detail(int bno,
                         Model model,
                         HttpServletRequest request) {

        BoardDetailResponseDto dto = service.detail(bno);
        model.addAttribute("bbb", service.detail(bno));

        String ref = request.getHeader("Referer");
        model.addAttribute("ref" , ref);

        return "/board/detail";
    }


}
