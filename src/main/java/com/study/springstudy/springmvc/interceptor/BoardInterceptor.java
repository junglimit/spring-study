package com.study.springstudy.springmvc.interceptor;

import com.study.springstudy.springmvc.chap04.service.BoardService;
import com.study.springstudy.springmvc.chap05.dto.response.LoginUserInfoDto;
import com.study.springstudy.springmvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.study.springstudy.springmvc.util.LoginUtil.*;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class BoardInterceptor implements HandlerInterceptor {

    private final BoardService boardService;

    // preHandle을 구현하여
    // 로그인을 안한 회원은 글쓰기, 글수정, 글삭제 요청을 거부할 것!
    // 거부하고 로그인 페이지로 리다이렉션할 것!
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!isLoggedIn(request.getSession())) {

            String redirectUri = request.getRequestURI();
            if(redirectUri.equals("/board/delete")) {
                redirectUri = "/board/list";
            }
            log.info("origin: {}", redirectUri);
            response.sendRedirect("/members/sign-in?message=login-required&redirect=" + redirectUri);
            return false;
        }

        // 삭제요청이 들어오면 서버에서 한번 더 관리자인지 본인의 글인지 체크
        if (request.getRequestURI().startsWith("/board/delete")) {
            LoginUserInfoDto login = (LoginUserInfoDto) request.getSession().getAttribute("login");
            String account = boardService.detail(Integer.parseInt(request.getParameter("bno"))).getAccount();
            if (!(account.equals(login.getAccount()) || login.getAccount() == "ADMIN")) {
                response.sendRedirect("/board/list");
                return false;
            }
        }


        return true;
    }

}
