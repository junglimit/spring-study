package com.study.springstudy.core.chap02;

/*

    @solution
    - DIP 해결을 위해 구체적인 객체 대신
      추상적인 역할에 의존하게 코드를 개선

      @problem
      - 추상화를 했지만
      여전히 코드를 직전 변경해야함
 */


public class Hotel {

    // 레스토랑
//    private WesternRestaurant restaurant = new WesternRestaurant();
    private Restaurant restaurant = new AsianRestaurant();

    // 헤드쉐프
//    private JannChef headChef = new JannChef();
    private Chef headChef = new KimuraChef();


    // 호텔 소개 기능
    public void inform() {
        System.out.printf("우리 호텔의 레스토랑은 %s입니다. " +
                "그리고 헤드쉐프는 %s입니다.\n", restaurant.getClass().getSimpleName(), headChef.getClass().getSimpleName());

        restaurant.order();
    }
}
