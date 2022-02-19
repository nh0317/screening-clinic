package com.reservation.screeningclinic.domain.reservation;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
public class ReservationForm {
    private Long screenClinicIdx;
    private String date;
    private String time;
}
