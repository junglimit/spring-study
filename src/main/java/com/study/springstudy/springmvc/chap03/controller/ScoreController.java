package com.study.springstudy.springmvc.chap03.controller;

import com.study.springstudy.springmvc.chap03.dto.ScorePostDto;
import com.study.springstudy.springmvc.chap03.entity.Score;
import com.study.springstudy.springmvc.chap03.repository.ScoreJdbcRepository;
import com.study.springstudy.springmvc.chap03.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
    # 요청 URL
    1. 학생 성적정보 등록화면을 보여주고 및 성적정보 목록조회 처리
    - /score/list : GET

    2. 성적 정보 등록 처리 요청
    - /score/register : POST

    3. 성적정보 삭제 요청
    - /score/remove : POST

    4. 성적정보 상세 조회 요청
    - /score/detail : GET
 */
@Controller
@RequestMapping("/score")
@RequiredArgsConstructor
public class ScoreController {

    // 의존객체 설정
    private final ScoreRepository repository;

//
//    public ScoreController(ScoreRepository repository) {
//        this.repository = repository;
//    }

    @GetMapping("/list")
    public String list(Model model) {
        System.out.println("/score/list : GET!");

        List<Score> scoreList = repository.findAll();
        model.addAttribute("sList", scoreList);

        return "score/score-list";
    }

    @PostMapping("/register")
    public String register(ScorePostDto dto) {
        System.out.println("/score/register : POST!");
        System.out.println("dto = " + dto);

        // 데이터베이스 저장
        Score score = new Score(dto);
        repository.save(score);

        // 다시 조회창으로 돌아감
        // 포워딩이 아닌 리다이렉트로 재요청을 넣어야 다시 db를 조회함
        return "redirect:/score/list";
    }

    @PostMapping("/remove")
    public String remove() {
        System.out.println("/score/remove : POST!");
        return "";
    }

    @GetMapping("/detail")
    public String detail(long stuNum, Model model) {
        System.out.println("/score/detail : GET!");
//        System.out.println("stuNum = " + stuNum);
        // 1. 상세조회를 원하는 학번을 읽기

        // 2. DB에 상세조회 요청
        Score score = repository.findOne(stuNum);
        // 3. DB에서 조회한 회원정보 JSP 에게 전달

        model.addAttribute("s",score);


        return "score/score-detail";
    }

}
