package com.complexica.test.service;

import org.openweathermap.WeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class WeatherService {
    @Value("${openweathermap.url}")
    private String openWeatherMapAppURL;

    @Value("${openweathermap.appKey}")
    private String openWeatherMapAppKey;

    @Autowired
    private RestTemplate restTemplate;

    @Cacheable("forecast")
    public ResponseEntity<WeatherDTO> getCityWeatherAtDate(String cityName, Date date, String units) {
        final String url = String.format("%s/forecast?q=%s&appid=%s&units=%s", openWeatherMapAppURL, cityName, openWeatherMapAppKey, units);
        return restTemplate.getForEntity(url, WeatherDTO.class);
    }

}
