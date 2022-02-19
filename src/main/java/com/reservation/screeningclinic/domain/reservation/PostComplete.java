package com.reservation.screeningclinic.domain.reservation;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class PostComplete {
    private Long screenClinicIdx;
    private Long reservationIdx;
    private String currentTime;
    private String date;
}
