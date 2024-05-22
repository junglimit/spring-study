package com.study.springstudy.springmvc.chap04.common;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Page {

    private int pageNo; // 클라이언트가 요청한 페이지 번호
    private int amount; // 클라이언트가 요청한 한페이지당 게시물 목록 수

    // 첫 페이지 기본값 설정
    public Page() {
        this.pageNo = 1;
        this.amount = 6;
    }

    public void setAmount(int amount) {
        if(amount < 6 || amount > 60) {
            this.amount = 6;
            return;
        }
    }

    public void setPageNo(int pageNo) {
        if(pageNo < 1 || pageNo> Integer.MAX_VALUE) {
            this.pageNo = 1;
            return;
        }
    }

    // 필드 셋팅 없이도 getter 를 만들면 mybatis 가 사용할 수 있게 함
//    public int getApple() {
//        System.out.println("getApple call!!!!!!");
//        return 5;
//    }

    public int getPageStart() {
        return  (this.pageNo - 1) * this.amount;

    }


}
