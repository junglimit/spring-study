package com.study.springstudy.webservlet.chap02.v2;


import com.study.springstudy.webservlet.View;
import com.study.springstudy.webservlet.chap02.v2.controller.JoinController;
import com.study.springstudy.webservlet.chap02.v2.controller.SaveController;
import com.study.springstudy.webservlet.chap02.v2.controller.ShowController;
import com.study.springstudy.webservlet.chap02.v2.controller.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


// 브라우저의 /chap02/v1/~ 으로 시작하는 요청을 처리하는 서블릿
@WebServlet("/chap02/v2/*")
public class FrontControllerV2 extends HttpServlet {


    // key = 요청 URI, value: 요청에 맞는 하위 컨트롤러 객체
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerV2() {
        controllerMap.put("/chap02/v2/join", new JoinController());
        controllerMap.put("/chap02/v2/save", new SaveController());
        controllerMap.put("/chap02/v2/show", new ShowController());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("브라우저 요청이 들어옴!!");

        // 들어온 요청 구분하기
        String uri = req.getRequestURI();
        System.out.println("uri = " + uri);

        ControllerV2 controller = controllerMap.get(uri);

        View view = controller.process(req, resp);
        view.render(req, resp);


    }
}
