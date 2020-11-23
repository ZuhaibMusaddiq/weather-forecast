package org.openweathermap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WeatherData {
    public int dt;
    public WeatherAttribute main;
    public List<Weather> weather;
    public Clouds clouds;
    public Wind wind;
    public int visibility;
    public int pop;
    public Sys sys;
    public String dt_txt;
}
