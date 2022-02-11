package com.reservation.screeningclinic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
@NoArgsConstructor
@Getter
public class BreakTimeInfo {

    private Long breakTimeIdx;

    private Long screenClinicIdx;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
