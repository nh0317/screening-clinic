package com.reservation.screeningclinic.util;

import com.reservation.screeningclinic.config.DefaultGlobalPropertyConfig;
import com.reservation.screeningclinic.domain.geocode.GetGeoRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class GeocoderService {
    @Autowired
    DefaultGlobalPropertyConfig globalPropertyConfig;
    public Map<String, Double> getGeoDataByAddress(String address) {
        RestTemplate restTemplate = new RestTemplate();
        /* 헤더정보세팅 */
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-NCP-APIGW-API-KEY-ID", globalPropertyConfig.getNaverId());
        httpHeaders.add("X-NCP-APIGW-API-KEY", globalPropertyConfig.getNaverSecret());

        String url = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query="+address;
        ResponseEntity<GetGeoRes> result;
        Map<String, Double> coords = new HashMap <>();
        try {
            result= restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(httpHeaders), GetGeoRes.class);
            if(result.getBody().getAddresses().isEmpty())
                return coords;
            coords.put("latitude", Double.valueOf(result.getBody().getAddresses().get(0).getY()));
            coords.put("longitude", Double.valueOf(result.getBody().getAddresses().get(0).getX()));
            log.info("주소 : "+address+" -> 위도 : "+coords.get("latitude") +" 경도 : "+coords.get("longitude"));
        }catch (Exception e){
            throw e;
        }
        return coords;

    }
}