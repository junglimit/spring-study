package com.study.springstudy.springmvc.interceptor;


import com.study.springstudy.springmvc.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 로그인 한 회원은 회원가입 페이지와 로그인 페이지에 접근 차단
@Configuration // 컴포넌트 시리즈
@Slf4j
public class AfterLoginInterceptor implements HandlerInterceptor {

    // 클라이언트의 요청이 컨트롤러에 들어가기 전에 해야할 일 명시
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("after login interceptor execute!");
        if(LoginUtil.isLoggedIn(request.getSession())){
            response.sendRedirect("/");

        return false; // true 일경우 컨트롤러 진입 허용

        }
        return true;
    }
}
