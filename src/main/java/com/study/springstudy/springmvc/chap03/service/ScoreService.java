package com.study.springstudy.springmvc.chap03.service;

// 컨트롤러와 레파지토리 사이에서 중간 처리를 담당
// 트랜잭션 처리, 데이터 가공 등

import com.study.springstudy.springmvc.chap03.dto.*;
import com.study.springstudy.springmvc.chap03.entity.Score;
import com.study.springstudy.springmvc.chap03.mapper.ScoreMapper;
import com.study.springstudy.springmvc.chap03.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// 의존관계 : Controller -> Service -> Repository

@RequiredArgsConstructor
@Service
public class ScoreService {

//    private final ScoreRepository repository;
    private final ScoreMapper repository;

    // 목록 조회 중간처리
    // - DB 에서 조회한 목록중 불필요하거나 민감한 정보는 가공하여 처리

    public List<ScoreListResponseDto> getList(String sort) {
        List<Score> scoreList = repository.findAll(sort);

        return scoreList.stream()
                .map(s-> new ScoreListResponseDto(s))
                .collect(Collectors.toList());
    }

    // 저장 중간처리

    public boolean insert(ScorePostDto dto) {
        return repository.save(new Score(dto));
    }

    // 삭제 중간처리
    public boolean deleteScore(long stuNum) {
        return repository.delete(stuNum);
    }

    // 개별조회 중간처리
    public ScoreDetailResponseDto retrieve(long stuNum) {
        Score score = repository.findOne(stuNum);

       RankDto result = repository.findRankByStuNum(stuNum);

        ScoreDetailResponseDto dto = new ScoreDetailResponseDto(score, result.getRank(), result.getCnt());
        return dto;
    }

    // 수정 완료를 위해 서비스 클래스에서 dto 를 entity 로 변환
    public void update(ScoreModifyRequestDto dto) {


        repository.updateScore(new Score(dto));

    }
}
