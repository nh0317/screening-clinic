package com.reservation.screeningclinic.controller;

import com.reservation.screeningclinic.domain.reservation.PostComplete;
import com.reservation.screeningclinic.domain.reservation.ReservationForm;
import com.reservation.screeningclinic.domain.reservation.ReservationInfo;
import com.reservation.screeningclinic.domain.screenclinic.ScreenClinicInfo;
import com.reservation.screeningclinic.service.ReservationService;
import com.reservation.screeningclinic.service.ScreenClinicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@Slf4j
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @Autowired
    ScreenClinicService screenClinicService;

    @GetMapping("/reservation/{screenClinicIdx}")
    public String getReservation(@PathVariable("screenClinicIdx")Long screenClinicIdx, Model model){
        ScreenClinicInfo screenClinicInfo = screenClinicService.getScreenClinicInfo(screenClinicIdx);
        model.addAttribute("screenClinic", screenClinicInfo);
        model.addAttribute("screenClinicIdx", screenClinicIdx);
        model.addAttribute("reservationTimeList", reservationService.getReservationTime(screenClinicIdx));
        model.addAttribute("cntReservation", reservationService.cntReservation(screenClinicIdx));
        return "예약하기2";
    }


    @GetMapping("/check_reservation/{screenClinicIdx}")
    public String getCheckReservation(@PathVariable("screenClinicIdx")Long screenClinicIdx, Model model){
        ScreenClinicInfo screenClinicInfo = screenClinicService.getScreenClinicInfo(screenClinicIdx);
        model.addAttribute("screenClinic", screenClinicInfo);
//        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH : mm"));
        String currentTime = "17 : 12";
        model.addAttribute("startTime", 17+" : 00");
        model.addAttribute("endTime", 18+" : 00");
        model.addAttribute("currentTime", currentTime);
//        model.addAttribute("startTime", LocalTime.now().getHour()+" : 00");
//        model.addAttribute("endTime", (LocalTime.now().getHour()+1)+" : 00");
        model.addAttribute("date",LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
//        List<ReservationInfo> reservations = reservationService.getReservation(1L, LocalTime.now().getHour(),
//                LocalDate.now());

//        List<ReservationInfo> reservations = reservationService.getReservation(screenClinicIdx,
//                LocalTime.now(), LocalDate.now());
        List<ReservationInfo> reservations = reservationService.getReservation(screenClinicIdx,
                LocalTime.of(17,12), LocalDate.now());
        model.addAttribute("reservationList", reservations);
        return "예약확인";
    }


    @PostMapping("/reserve")
    @ResponseBody
    public int postRsz(@RequestBody ReservationForm reservationForm,Model model) {
        Long userIdx=1L;
        log.info(reservationForm.toString());
        reservationService.save(reservationForm,userIdx);
//        String url = "screenClinic";
//        log.info(url);
//        model.addAttribute("data", new Message("예약이 완료되었습니다.", url));
//        mav.setViewName("message");
        ScreenClinicInfo screenClinicInfo = screenClinicService.getScreenClinicInfo(reservationForm.getScreenClinicIdx());
//        model.addAttribute("screenClinic", screenClinicInfo);
//        model.addAttribute("screenClinicIdx", reservationForm.getScreenClinicIdx());
//        model.addAttribute("reservationTimeList", reservationService.getReservationTime(reservationForm.getScreenClinicIdx()));
//        model.addAttribute("cntReservation", reservationService.cntReservation(reservationForm.getScreenClinicIdx()));
        return reservationService.cntReservation(reservationForm.getScreenClinicIdx());
    }

    @PostMapping( "/check_reservation/complete")
    public String postComplete(@RequestBody PostComplete postComplete, Model model){
        List<ReservationInfo> reservationInfoList = reservationService.completeReservation(postComplete);
        model.addAttribute("reservationList",reservationInfoList);
        return "예약확인 :: reservationTable";
    }

}
