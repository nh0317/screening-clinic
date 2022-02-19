package com.reservation.screeningclinic.domain.screenclinic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
@Setter
@Alias("ScreenClinicInfo")
@ToString
public class ScreenClinicInfo {

    private Long screenClinicId;

    private String city;

    private String county;

    private String clinicName;

    private String address;

    private String weekOperationTime;

    private String satOperationTime;

    private String holidayOperationTime;

    private String tel;

    private String publicHealthCenter;

    private String telOfPublicHealthCenter;

    private String disabilityConvenience;

    private String updatedAt;

    private LocalDateTime createdAt;

    // 0: 문진표 작성 불가능 1: 문진표 작성 가능
    private Boolean canQuestionnaire;

    // 위도
    private Double latitude;

    // 경도
    private Double longitude;
}