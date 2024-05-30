package com.study.springstudy.springmvc.chap05.api;


import com.study.springstudy.springmvc.chap05.dto.request.SignUpDto;
import com.study.springstudy.springmvc.chap05.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입 양식 열기
    @GetMapping("/sign-up")
//    @ResponseBody // 텍스트 포워딩 , 안쓰면 jsp 포워딩
    public void signUp() {
        log.info("/members/sign up GET : forwarding to sign-up.jsp");
//        return "members/sign-up"; URL 과 경로이름이 같으면 void 타입으로 설정해도 알아서 읽어옴
    }

    // 회원가입 요청 처리
    @PostMapping("/sign-up")
    public String signUp(SignUpDto dto) {
        log.info("/members/sign up POST");
        log.debug("parameter: {}", dto);

        boolean flag = memberService.join(dto);
        return flag ? "redirect:/board/list" :"redirect:/members/sign-up";
    }

    // 아이디, 이메일 중복검사 비동기 요청 처리
    @GetMapping("check")
    @ResponseBody // 이 클래스는 일반 컨트롤러라서 (rest 아님) 꼭 붙여줘야함
    public ResponseEntity<?> check(String type, String keyword) {
        boolean flag = memberService.checkIdentifier(type, keyword);
        log.info("{} 중복체크 결과? {}", type, flag);
        return ResponseEntity.ok().body(flag);
    }



}
