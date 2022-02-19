package com.reservation.screeningclinic.domain.geocode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class GetGeoRes {
    String status;
    Meta meta;
    List<GeoAddress> addresses;
    String errorMessage;
}
