package com.study.springstudy.springmvc.chap05.dto.response;

import lombok.*;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReactionDto {

    // 좋아요 처리를 위해 클라이언트에 보낼 JSON

    private int likeCount; // 갱신된 좋아요 수
    private int dislikeCount;
    private String userReaction; // 현재 리액션 상태


}
