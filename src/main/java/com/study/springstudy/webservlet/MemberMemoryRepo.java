package com.study.springstudy.webservlet;


import com.study.springstudy.webservlet.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberMemoryRepo {
    private MemberMemoryRepo() {
    }
    private static MemberMemoryRepo repo = new MemberMemoryRepo();

    public static MemberMemoryRepo getInstance() {
        return repo;
    }

    // 필드
    private List<Member> memberList = new ArrayList<>();

    public void save(Member member) {
        repo.memberList.add(member);
    }
    // 멤버 전체 조회 기능
    public List<Member> findAll() {
        return memberList;
    }
}
