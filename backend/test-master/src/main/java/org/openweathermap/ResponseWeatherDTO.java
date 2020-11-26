package org.openweathermap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResponseWeatherDTO {
    private String cod;
    private int message;
    private int cnt;
    private List<WeatherData> list;
    private City city;
}
