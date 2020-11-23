package org.openweathermap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WeatherDTO {
    public String cod;
    public int message;
    public int cnt;
    public List<WeatherData> list;
    public City city;
}
