package com.reservation.screeningclinic.controller;

import com.reservation.screeningclinic.config.DefaultGlobalPropertyConfig;
import com.reservation.screeningclinic.domain.screenclinic.GetScreenClinic;
import com.reservation.screeningclinic.domain.screenclinic.ScreenClinicInfo;
import com.reservation.screeningclinic.mapper.ScreenClinicMapper;
import com.reservation.screeningclinic.service.ReservationService;
import com.reservation.screeningclinic.service.ScreenClinicService;
import com.reservation.screeningclinic.util.GeocoderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class ScreenClinicController {
    @Autowired
    ScreenClinicService screenClinicService;

    @Autowired
    GeocoderService geocoderService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    ScreenClinicMapper screenClinicMapper;
    @Autowired
    DefaultGlobalPropertyConfig defaultGlobalPropertyConfig;

    @GetMapping("/screenClinic/{screenClinicIdx}")
    public String getScreenClinic(@PathVariable("screenClinicIdx")Long screenClinicIdx, Model model){
        ScreenClinicInfo screenClinicInfo = screenClinicService.getScreenClinicInfo(screenClinicIdx);
        model.addAttribute("screenClinicIdx", screenClinicIdx);
        model.addAttribute("screenClinic", screenClinicInfo);
        model.addAttribute("cntReservation", reservationService.cntReservation(screenClinicIdx));
        return "screenClinic";
    }

    @GetMapping("/")
        public String getRoot(Model model) {
        model.addAttribute("kakaoUrl", defaultGlobalPropertyConfig.getKakaoUrl());
        return "index";
    }
//    @GetMapping("/geocode")
//    public String getGeocode(){
//        List<ScreenClinicInfo> clinicInfos = screenClinicService.getAllScreenClinicInfo();
//        for (ScreenClinicInfo clinic : clinicInfos) {
//            log.info(clinic.toString());
//            Map<String, Double> geoDataByAddress = geocoderService.getGeoDataByAddress(clinic.getAddress());
//            if (geoDataByAddress.isEmpty())
//                continue;
//            screenClinicMapper.insertLongLat(geoDataByAddress.get("longitude"), geoDataByAddress.get("latitude"), clinic.getScreenClinicId());
//        }
//        return "index";
//    }

    @PostMapping("getScreenClinic")
    @ResponseBody
    public List<ScreenClinicInfo> getScreenClinic(@RequestBody GetScreenClinic getScreenClinic){
        log.info(getScreenClinic.getLongitude()+" "+getScreenClinic.getLatitude());
        List<ScreenClinicInfo> clinics = screenClinicService.getScreenClinicByDistance(getScreenClinic);
        return clinics;
    }
}
