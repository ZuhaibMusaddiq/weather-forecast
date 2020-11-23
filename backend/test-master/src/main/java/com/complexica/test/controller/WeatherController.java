package com.complexica.test.controller;

import com.complexica.test.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.openweathermap.WeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/forecasts")
    @ResponseBody
    public ResponseEntity<?> getCityWeatherAtDate(@RequestParam String cityName, @RequestParam Date date, @RequestParam(required = false, defaultValue = "metric") String units) {
        final ResponseEntity<WeatherDTO> responseEntity = weatherService.getCityWeatherAtDate(cityName, date, units);
        return ResponseEntity.ok(responseEntity.getBody());
    }
}
