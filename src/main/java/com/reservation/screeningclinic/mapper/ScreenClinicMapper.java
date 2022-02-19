package com.reservation.screeningclinic.mapper;

import com.reservation.screeningclinic.domain.screenclinic.ScreenClinicInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScreenClinicMapper {
    public ScreenClinicInfo getScreenClinicInfo(@Param("screenClinicIdx") Long screenClinicIdx);
    public List<ScreenClinicInfo> getAll();
    public int insertLongLat(@Param("longitude")Double longitude, @Param("latitude")Double latitude,
                             @Param("screenClinicIdx") Long screenClinicIdx);
    public List<ScreenClinicInfo> findByDistance(@Param("userLatitude") Double userLatitude,
                                                 @Param("userLongitude") Double userLongitude,
                                                 @Param("distance")Double distance);
}
