package com.study.springstudy.springmvc.chap05.api;

import com.study.springstudy.springmvc.chap05.dto.request.ReplyPostDto;
import com.study.springstudy.springmvc.chap05.dto.response.ReplyDetailDto;
import com.study.springstudy.springmvc.chap05.entity.Reply;
import com.study.springstudy.springmvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/replies")
@RequiredArgsConstructor
@Slf4j
public class ReplyApiController {
    private final ReplyService replyService;


    // 댓글 목록 조회 요청
    // URL : /api/vi/replies/원본글번호  - GET -> 목록조회
    // @PathVariable : URL에 붙어있는 변수값을 읽는 아노테이션
    @GetMapping("/{bno}")
    public ResponseEntity<?> list (@PathVariable long bno) {

        if (bno == 0) {
            String message = "글 번호는 0번이 될 수 없습니다.";
            log.warn(message);
            return ResponseEntity.badRequest().body(message);
        }

        log.info("/api/v1/replies/{} : GET ",bno);

        List<ReplyDetailDto> replies = replyService.getReplies(bno);

        log.debug("first reply : {}", replies.get(0));



        return ResponseEntity.ok().body(replies);

    }

    // 댓글 생성 요청
    // url 생략
    // @RequestBody : 클라이언트가 전송한 데이터를 JSON 으로 받아서 파싱
    @PostMapping
    public ResponseEntity<?> posts(@Validated @RequestBody ReplyPostDto dto
    , BindingResult result // 입력값 검증 결과 데이터를 가지고 있는 객체
    ) {

        log.info("/api/v1/replies : POST");
        log.debug("parameter : {}", dto);

        if (result.hasErrors()) {

            Map<String, String> errors = makeValidationMessageMap(result);

            return ResponseEntity
                    .badRequest()
                    .body(errors);
        }

        boolean flag = replyService.register(dto);

        if(!flag) return ResponseEntity.
                internalServerError()
                .body("댓글 등록 실패!");

        return ResponseEntity
                .ok()
                .body(replyService.getReplies(dto.getBno()));
    }
    private Map<String, String> makeValidationMessageMap(BindingResult result) {

        Map<String, String> errors = new HashMap<>();

        // 에러정보가 모여있는 리스트
        List<FieldError> fieldErrors = result.getFieldErrors();

        for (FieldError error : fieldErrors) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return errors;
    }


    // 삭제 처리 요청
    @DeleteMapping("/{rno}")
    public ResponseEntity<?> delete (@PathVariable long rno){
        List<ReplyDetailDto> dtoList = replyService.remove(rno);


        return ResponseEntity
                .ok()
                .body(dtoList);
    }


}
