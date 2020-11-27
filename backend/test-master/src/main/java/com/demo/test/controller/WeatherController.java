package com.demo.test.controller;

import com.demo.test.model.dto.WeatherDTO;
import com.demo.test.service.WeatherService;
import com.demo.test.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/forecast")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<List<WeatherDTO>> getWeatherForecastByCity(@PathVariable("city") String cityName, @RequestParam Integer days, @RequestParam(required = false, defaultValue = "metric") String units) {
        try {
            List<WeatherDTO> list = weatherService.getWeatherForecastByCity(cityName, DateUtil.todayPlusDays(days), units);

            if (CollectionUtils.isEmpty(list)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
