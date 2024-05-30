package com.study.springstudy.springmvc.chap05.api;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/members")
@Slf4j
public class MemberController {


    @GetMapping("/sign-up")
//    @ResponseBody // 텍스트 포워딩 , 안쓰면 jsp 포워딩
    public void signUp() {
        log.info("/members/sign up GET : forwarding to sign-up.jsp");
//        return "members/sign-up"; URL 과 경로이름이 같으면 void 타입으로 설정해도 알아서 읽어옴
    }


}
