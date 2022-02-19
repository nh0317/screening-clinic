package com.reservation.screeningclinic.domain.geocode;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AddressElements{
    List<String> types;
    String longName;
    String shortName;
    String code;
}