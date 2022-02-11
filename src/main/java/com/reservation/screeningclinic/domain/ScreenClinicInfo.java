package com.reservation.screeningclinic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
public class ScreenClinicInfo {

    private Long screenClinicIdx;

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

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    // 0: 문진표 작성 불가능 1: 문진표 작성 가능
    private Boolean canQuestionnaire;

    // 위도
    private Double latitude;

    // 경도
    private Double longitude;
}