package com.demo.test.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherDTO {
    private int dt;
    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private int pressure;
    private int sea_level;
    private int grnd_level;
    private int humidity;
    private double temp_kf;
    private int weather_id;
    private String main;
    private String description;
    private String icon;
    private int all;
    private double speed;
    private int deg;
    private int visibility;
    private int pop;
    private String pod;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date dt_txt;
    private String cod;
    private int message;
    private int cnt;
    private int city_id;
    private String name;
    private double lat;
    private double lon;
    private String country;
    private int population;
    private int timezone;
    private int sunrise;
    private int sunset;
}
