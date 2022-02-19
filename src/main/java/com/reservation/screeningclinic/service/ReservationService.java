package com.reservation.screeningclinic.service;

import com.reservation.screeningclinic.domain.reservation.PostComplete;
import com.reservation.screeningclinic.domain.reservation.ReservationForm;
import com.reservation.screeningclinic.domain.reservation.ReservationInfo;
import com.reservation.screeningclinic.domain.reservation.ReservationTime;
import com.reservation.screeningclinic.domain.screenclinic.ScreenClinicInfo;
import com.reservation.screeningclinic.mapper.ReservationMapper;
import com.reservation.screeningclinic.mapper.ScreenClinicMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class ReservationService {
    @Autowired
    ReservationMapper reservationMapper;

    @Autowired
    ScreenClinicMapper screenClinicMapper;
    public void save(ReservationForm reservationForm, Long userIdx){
//        Long screenClinicIdx =1L;
        ReservationInfo reservationInfo = new ReservationInfo(userIdx,reservationForm.getScreenClinicIdx(),
                reservationForm.getDate(),reservationForm.getTime(),userIdx);
        reservationMapper.save(reservationInfo);
    }

    public List<ReservationInfo> getReservation(Long screenClinicIdx, LocalTime currentTime, LocalDate date){
        log.info("reservation");
        List<ReservationInfo> reservationInfos =
                reservationMapper.getReservationInfoByScreenClinicIdxAndTime(screenClinicIdx,currentTime,date);
        log.info(reservationInfos.size() + "");
        reservationInfos.forEach(r->log.info(r.getUserIdx()+" "+r.getName()+" "+r.getState()));
        reservationInfos.forEach(r->log.info(r.toString()));
        return reservationInfos;
    }

    public List<ReservationInfo> completeReservation(PostComplete postComplete){
        ReservationInfo reservationInfo = reservationMapper.getReservationInfo(postComplete.getReservationIdx());
        log.info(reservationInfo.toString());
        reservationInfo.setState(true);
        reservationMapper.save(reservationInfo);
        LocalTime startTime = LocalTime.parse(postComplete.getCurrentTime(),DateTimeFormatter.ofPattern("HH : mm "));
        List<ReservationInfo> reservations = reservationMapper
                .getReservationInfoByScreenClinicIdxAndTime(postComplete.getScreenClinicIdx(), startTime,
                        LocalDate.parse(postComplete.getDate(), DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 ")));
        reservations.forEach(r->log.info(r.toString()));
        return reservations;
    }

    public List<ReservationTime> getReservationTime(Long screenClinicIdx){
        ScreenClinicInfo screenClinicInfo = screenClinicMapper.getScreenClinicInfo(screenClinicIdx);
        DayOfWeek currentDate = LocalDate.now().getDayOfWeek();
        LocalTime startTime;
        LocalTime endTime;
        String[] time = new String[2];
        try {
            switch (currentDate) {
                case SUNDAY:
                    String holidayOperationTime = screenClinicInfo.getHolidayOperationTime();
                    time = holidayOperationTime.split("~");
                    break;
                case SATURDAY:
                    String satOperationTime = screenClinicInfo.getSatOperationTime();
                    time = satOperationTime.split("~");
                    break;
                default:
                    String weekOperationTime = screenClinicInfo.getWeekOperationTime();
                    time = weekOperationTime.split("~");
                    break;
            }
//            log.info(currentDate.getDisplayName(TextStyle.SHORT, Locale.KOREA));
            startTime = LocalTime.parse(time[0],DateTimeFormatter.ofPattern("HH:mm"));
            endTime = LocalTime.parse(time[1],DateTimeFormatter.ofPattern("HH:mm"));
        }catch (Exception e){
            return null;
        }
        List<ReservationTime> reservationTimes = new ArrayList<>();
        for (int i = startTime.getHour();i<=endTime.getHour();i++){
            ReservationTime reservationTime;
            if (endTime.isBefore(LocalTime.of(i,1))){
                continue;
            }
            else if (endTime.isBefore(LocalTime.of(i,31))) {
                reservationTime = new ReservationTime(i + ":00", null);
            }
            else
                reservationTime = new ReservationTime(i+":00",i+":30");
            reservationTimes.add(reservationTime);
        }
        return reservationTimes;
    }
    public Integer cntReservation(Long screenClinicIdx){
        return reservationMapper.cntReservation(screenClinicIdx);
    }
}
