package com.reservation.screeningclinic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@NoArgsConstructor
@Getter
public class ReservationInfo {

    private Long reservationIdx;

    // 예약자 정보
    private Long userIdx;

    private Long screenClinicIdx;

    private LocalDate reservationDate;

    private LocalTime reservationTime;

    // 0: 미완료 1: 완료
    private Boolean state;

    private String modBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}