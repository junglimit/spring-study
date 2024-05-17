package com.study.springstudy.database.chap01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringJdbcTest {

    // test 할땐 Autowired 붙임
    @Autowired
    SpringJdbc springJdbc; // 테스트 할 클래스 필드로 생성

    // 단위 테스트 프레임워크 : JUnit5
    // 테스트 == 단언 (Assertion) 단언컨대, 확신
    @Test
    @DisplayName("사람의 정보를 입력하면 데이터베이스에 반드시 저장되어야 한다.")
    void saveTest() {
        // gwt 패턴
        // given : 테스트에 주어질 데이터
        Person p = new Person(300, "삼백이",34);

        // when : 테스트 상황
        int result = springJdbc.save(p);

        // then : 테스트 결과 단언
        assertEquals(1, result);


    }

    @Test
    @DisplayName("아이디가 주어지면 해당 아이디의 사람 정보가 데이터베이스로부터 삭제 되어야 한다.")
    void deleteTest() {
        //given
        long id = 77;
        //when
        boolean flag = springJdbc.delete(id);
        //then
        assertTrue(flag);
    }
    @Test
    @DisplayName("새로운 이름과 나이를 전달하면 사람의 정보가 데이트베이스에서 수정된다.")
        void modifyTest () {
            //given
        Person person = new Person(77, "팔팔이", 8);
        //when
        boolean result = springJdbc.update(person);
        //then

        assertTrue(result);
        }

        @Test
        @DisplayName("사람 정보를 전체 조회하면 결과 건수는 4건이며, 첫번째 사람의 이름은 \'팔팔이\'다.")
        void findAllTest () {
            //given

            //when
            List<Person> people = springJdbc.findAll();
            //then
            people.forEach(System.out::println);
            assertEquals(4, people.size());
            assertEquals("팔팔이", people.get(0).getPersonName());
        }
        @Test
        @DisplayName("사람 정보를 아이디로 단일 조회시 아이디가 77인 사람의 이름은 \'팔팔이\' 이고 나이는 8이다")
        void findOneTest () {
            //given
            long id = 77;
            //when
            Person one = springJdbc.findOne(id);
            //then
            System.out.println("one = " + one);
            assertNotNull(one);
            assertEquals("팔팔이", one.getPersonName());
            assertEquals(8,one.getPersonAge());
        }


    }


