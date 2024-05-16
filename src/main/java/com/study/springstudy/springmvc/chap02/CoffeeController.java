package com.study.springstudy.springmvc.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/coffee/*")
public class CoffeeController {

    // get 요청만 받기
//    @RequestMapping("/order")
    @GetMapping("/order")
    public String order() {
        return "mvc/coffee-form";
    }

    // post 요청만 받기
//    @RequestMapping(value = "/result", method = RequestMethod.POST)
    @PostMapping("/result") // 스프링에서 위 코드를 줄여쓸 수 있게 만듬
    public String result(String menu, int price, Model model) {

        // 1. 주문 데이터 읽어오기

        // 2. jsp 에 보내서 렌더링
        model.addAttribute("mmm", menu);
        model.addAttribute("ppp", price);

        return "mvc/coffee-result";
    }
}
