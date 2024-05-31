package com.study.springstudy.springmvc.chap05.api;


import com.study.springstudy.springmvc.chap05.dto.request.LoginDto;
import com.study.springstudy.springmvc.chap05.dto.request.SignUpDto;
import com.study.springstudy.springmvc.chap05.dto.response.LoginUserInfoDto;
import com.study.springstudy.springmvc.chap05.service.LoginResult;
import com.study.springstudy.springmvc.chap05.service.MemberService;
import com.study.springstudy.springmvc.util.LoginUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        return flag ? "redirect:/member/sign-in" :"redirect:/members/sign-up";
    }

    // 아이디, 이메일 중복검사 비동기 요청 처리
    @GetMapping("check")
    @ResponseBody // 이 클래스는 일반 컨트롤러라서 (rest 아님) 꼭 붙여줘야함
    public ResponseEntity<?> check(String type, String keyword) {
        boolean flag = memberService.checkIdentifier(type, keyword);
        log.info("{} 중복체크 결과? {}", type, flag);
        return ResponseEntity.ok().body(flag);
    }

    // 로그인 양식 열기
    @GetMapping("/sign-in")
    public String signIn(HttpSession session , @RequestParam(required = false) String redirect) {


        // 로그인을 한 사람이 요청시 돌려보냄
//        if (LoginUtil.isLoggedIn(session)) {
//            return "redirect:/";
//        }

        session.setAttribute("redirect", redirect);

        log.info("/members/sign-in GET : forwarding to sign-in.jsp");
        return "members/sign-in";


    }

    // 로그인 요청 처리
    @PostMapping("sign-in")
    //
    public String signIn(LoginDto dto ,RedirectAttributes ra ,  HttpServletRequest request) {
        log.info("/members/sign-in POST");
        log.debug("parameter: {}", dto);

        // 세션 얻기
        HttpSession session = request.getSession();


        LoginResult result = memberService.authenticate(dto, session);

        // 로그인 검증 결과를 JSP 에게 보내기
        // redirect 를 사용하면 Model 객페를 사용할 수 없다.
        // Model 객체는 request 객체를 사용하는데 한번 요청이 끝나면 메모리에서 제거됨. ( redirect 는 요청이 2번씩 발생됨 )
        ra.addFlashAttribute("result", result);

        if(result == LoginResult.SUCCESS) {

            // 세션에 리다이렉트 URL 이 있다면
            String redirect = (String) session.getAttribute("redirect");
            if(redirect != null) {
                session.removeAttribute("redirect");
                return "redirect:" + redirect;
            }

            return "redirect:/index"; // 로그인 성공시

        }
        return "redirect:/members/sign-in"; // 로그인 실패시
    }

    @GetMapping("/sign-out")
    public String signOut(HttpSession session) {
        // 세션 구하기
//        HttpSession session = request.getSession(); -> HttpSession 으로 한번에 구할 수 있음

        // 세션에서 로그인 기록 삭제
        session.removeAttribute("login");

        // 세션 리셋
        session.invalidate();

        // 홈으로 보내기
        return "redirect:/";
    }



}
