package com.study.springstudy.springmvc.chap04.common;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Page {

    private int pageNo; // 클라이언트가 요청한 페이지 번호
    private int amount; // 클라이언트가 요청한 한페이지당 게시물 목록 수

    // 필드 셋팅 없이도 getter 를 만들면 mybatis 가 사용할 수 있게 함
//    public int getApple() {
//        System.out.println("getApple call!!!!!!");
//        return 5;
//    }

    public int getPageStart() {
        return  (this.pageNo - 1) * this.amount;
    }

}
