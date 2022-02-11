package com.reservation.screeningclinic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
public class GuestionnaireInfo {

    // 인덱스
    private Long questionnaireIdx;

    // 예약 인덱스
    private Long reservationIdx;

    // 다운로드 링크
    private String downloadLink;

    // 문진 내용
    private String context;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
