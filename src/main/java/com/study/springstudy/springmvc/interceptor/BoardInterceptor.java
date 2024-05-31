package com.study.springstudy.springmvc.interceptor;

import com.study.springstudy.springmvc.util.LoginUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class BoardInterceptor implements HandlerInterceptor {


    // preHandle 을 구현하여
    // 로그인을 안한 회원은 글쓰기, 글수정, 글삭제 요청을 거부하기
    //거부하고 로그인 페이지로 리다이렉션


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!LoginUtil.isLoggedIn(request.getSession())) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
