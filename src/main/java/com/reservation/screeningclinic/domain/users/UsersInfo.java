package com.reservation.screeningclinic.domain.users;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
// 사용자
public class UsersInfo {

    // 인덱스
    private Long userIdx;

    // 아이디
    private String id;

    // 비밀번호
    private String password;

    // 전화번호
    private String tel;

    // 권한 ADMIN/MEMBER
    private String role;

    // 이름
    private String name;

    // 상태 0: 활성 1: 탈퇴
    private Boolean state;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}