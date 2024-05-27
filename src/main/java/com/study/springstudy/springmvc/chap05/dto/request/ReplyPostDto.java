package com.study.springstudy.springmvc.chap05.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// 클라이언트가 서버에게 주는 DTO
// 여기에 입력검증 라이브러리 사용하기!

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ReplyPostDto {
    /*
        NotBlank : null 허용 X , 빈문자 X
        NotNull : null 만 허용 X
        NotEmpty : null 허용 O , 빈문자 X
     */


    @NotBlank
    @Size(min = 1, max = 300)
    private String text; // 댓글내용

    @NotBlank
    @Size(min = 2, max = 8)
    private String author; // 댓글 작성자

    @NotNull // Long 타입은 빈문자열 없음
    private Long bno; // 원본 글 번호
}
