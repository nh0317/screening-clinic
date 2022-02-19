package com.reservation.screeningclinic.service;


import com.reservation.screeningclinic.domain.screenclinic.GetScreenClinic;
import com.reservation.screeningclinic.domain.screenclinic.ScreenClinicInfo;
import com.reservation.screeningclinic.mapper.ScreenClinicMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ScreenClinicService {
    @Autowired
    ScreenClinicMapper screenClinicMapper;

    public ScreenClinicInfo getScreenClinicInfo(Long idx){
        ScreenClinicInfo screenClinicInfo = screenClinicMapper.getScreenClinicInfo(idx);
        log.info(screenClinicInfo.toString());
        return screenClinicInfo;
    }

    public List<ScreenClinicInfo> getAllScreenClinicInfo(){
        return screenClinicMapper.getAll();
    }
    public List<ScreenClinicInfo> getScreenClinicByDistance(GetScreenClinic getScreenClinic){
        return screenClinicMapper.findByDistance(getScreenClinic.getLatitude(), getScreenClinic.getLongitude(), 10D);
    }

}
