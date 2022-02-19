package com.reservation.screeningclinic.domain.reservation;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Alias("ReservationInfo")
public class ReservationInfo {

    private Long reservationIdx;

    // 예약자 정보
    private Long userIdx;

    private Long screenClinicIdx;

    private LocalDate reservationDate;

    private LocalTime reservationTime;

    // 0: 미완료 1: 완료
    private Boolean state;

    private Long modBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private String name;

    public ReservationInfo(Long userIdx, Long screenClinicIdx, String reservationDate, String reservationTime, Long modBy) {
        this.userIdx=userIdx;
        this.screenClinicIdx=screenClinicIdx;
        this.reservationDate = LocalDate.parse(reservationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.reservationTime = LocalTime.parse(reservationTime, DateTimeFormatter.ofPattern("HH:mm"));
        this.state=false;
        this.modBy=modBy;
    }

}