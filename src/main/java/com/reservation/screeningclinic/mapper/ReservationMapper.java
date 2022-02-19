package com.reservation.screeningclinic.mapper;

import com.reservation.screeningclinic.domain.reservation.ReservationInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Mapper
public interface ReservationMapper {
    int save(ReservationInfo reservationInfo);
    List<ReservationInfo> getReservationInfoByScreenClinicIdxAndTime(@Param("screenClinicIdx") Long screenClinicIdx,
                                                                     @Param("startTime") LocalTime startTime,
                                                                     @Param("reservationDate") LocalDate date);

    ReservationInfo getReservationInfo(@Param("reservationIdx") Long reservationIdx);
    Integer cntReservation(@Param("screenClinicIdx") Long screenClinicIdx);
}
