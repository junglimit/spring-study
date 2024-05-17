package com.study.springstudy.core.chap04;

/*

    @solution
    - 객체 생성의 제어권을 이 클래스에서
    다른 클래스로 이전한다.

    - 호텔 객체 생성시 반드시 객체를 전달하도록 강요

    // 제어의 역전(IoC) : 객체 생성의 제어권을 외부로 넘긴다.
    // 의존성 주입(DI) : 외부에서 생성된 객체를 주입받는 개념
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // Hotel 의 객체생성 제어권을 스프링에게 넘김
public class Hotel {

    // 레스토랑
//    private WesternRestaurant restaurant = new WesternRestaurant();
    // 필드 주입 방식 : 간편하지만 테스트 코드 작성이 어려움. 유연성이 떨어짐
//    @Autowired
    private final Restaurant restaurant;

    // 헤드쉐프
//    private JannChef headChef = new JannChef();
//    @Autowired
    private final Chef headChef;


    // 만약에 해당 클래스의 생성자가 단 한개뿐이라면 자동으로 @Autowired 를 붙임 ( 생략 되어있음 , 생성자가 하나만 있어야 함 )
    // 생성자 주입 : 불변성 보장, 문제 가능성 낮음. 대신 유지보수는 빡심 ( 이거쓰셈 )
    public Hotel(@Qualifier("wwww") Restaurant restaurant, Chef headChef) {
        this.restaurant = restaurant;
        this.headChef = headChef;
    }

    // 호텔 소개 기능
    public void inform() {
        System.out.printf("우리 호텔의 레스토랑은 %s입니다. " +
                "그리고 헤드쉐프는 %s입니다.\n", restaurant.getClass().getSimpleName(), headChef.getClass().getSimpleName());

        restaurant.order();
    }


    // 수정자 주입 : 유연하지만 객체 생성후에 주입 -> null 생길 가능성 있음 , 불변성 X
//    @Autowired
//    public void setRestaurant(Restaurant restaurant) {
//        this.restaurant = restaurant;
//    }
//    public Restaurant getRestaurant() {
//        return restaurant;
//    }
//
//    public Chef getHeadChef() {
//        return headChef;
//    }
//    @Autowired
//    public void setHeadChef(Chef headChef) {
//        this.headChef = headChef;
//    }
}
