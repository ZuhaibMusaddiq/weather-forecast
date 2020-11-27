package com.demo.test.service;

import com.demo.test.model.dto.WeatherDTO;
import com.demo.test.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.openweathermap.ResponseWeatherDTO;
import org.openweathermap.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WeatherService {
    @Value("${openweathermap.url}")
    private String openWeatherMapAppURL;

    @Value("${openweathermap.appKey}")
    private String openWeatherMapAppKey;

    @Autowired
    private RestTemplate restTemplate;

    @Cacheable("forecasts")
    public List<WeatherDTO> getWeatherForecastByCity(String cityName, LocalDate date, String units) {
        final String url = String.format("%s/forecast?q=%s&appid=%s&units=%s", openWeatherMapAppURL, cityName, openWeatherMapAppKey, units);
        log.debug("Calling weather api: " + url);
        ResponseEntity<ResponseWeatherDTO> response = restTemplate.getForEntity(url, ResponseWeatherDTO.class);

        if (response != null && response.hasBody()) {
            ResponseWeatherDTO responseWeatherData = response.getBody();
            List<WeatherDTO> list = new ArrayList<>();
            List<WeatherData> inRangeWeatherData = responseWeatherData.getList().stream()
                    .filter(w -> DateUtil.isTimeInDefaultRange(date, w.getDt_txt())).collect(Collectors.toList());

            if (!CollectionUtils.isEmpty(inRangeWeatherData)) {
                for (WeatherData data : inRangeWeatherData) {
                    list.add(WeatherDTO.builder()
                            .dt(data.getDt())
                            .temp(data.getMain() != null ? data.getMain().getTemp() : null)
                            .feels_like(data.getMain() != null ? data.getMain().getFeels_like() : null)
                            .temp_min(data.getMain() != null ? data.getMain().getTemp_min() : null)
                            .temp_max(data.getMain() != null ? data.getMain().getTemp_max() : null)
                            .pressure(data.getMain() != null ? data.getMain().getPressure() : null)
                            .sea_level(data.getMain().getSea_level())
                            .grnd_level(data.getMain() != null ? data.getMain().getGrnd_level() : null)
                            .humidity(data.getMain() != null ? data.getMain().getHumidity() : null)
                            .temp_kf(data.getMain() != null ? data.getMain().getTemp_kf() : null)
                            .weather_id(data.getWeather() != null && data.getWeather().size() > 0 ? data.getWeather().get(0).getId() : null)
                            .main(data.getWeather() != null && data.getWeather().size() > 0 ? data.getWeather().get(0).getMain() : null)
                            .description(data.getWeather() != null && data.getWeather().size() > 0 ? data.getWeather().get(0).getDescription() : null)
                            .icon(data.getWeather() != null && data.getWeather().size() > 0 ? data.getWeather().get(0).getIcon() : null)
                            .all(data.getClouds() != null ? data.getClouds().getAll() : null)
                            .speed(data.getWind() != null ? data.getWind().getSpeed() : null)
                            .deg(data.getWind() != null ? data.getWind().getDeg() : null)
                            .visibility(data.getVisibility())
                            .pop(data.getPop())
                            .pod(data.getSys() != null ? data.getSys().getPod() : null)
                            .dt_txt(data.getDt_txt())
                            .cod(responseWeatherData.getCod())
                            .message(responseWeatherData.getMessage())
                            .cnt(responseWeatherData.getCnt())
                            .city_id(responseWeatherData.getCity() != null ? responseWeatherData.getCity().getId() : null)
                            .name(responseWeatherData.getCity() != null ? responseWeatherData.getCity().getName() : null)
                            .lat(responseWeatherData.getCity() != null ? responseWeatherData.getCity().getCoord().getLat() : null)
                            .lon(responseWeatherData.getCity() != null ? responseWeatherData.getCity().getCoord().getLon() : null)
                            .country(responseWeatherData.getCity() != null ? responseWeatherData.getCity().getCountry() : null)
                            .population(responseWeatherData.getCity() != null ? responseWeatherData.getCity().getPopulation() : null)
                            .timezone(responseWeatherData.getCity() != null ? responseWeatherData.getCity().getTimezone() : null)
                            .sunrise(responseWeatherData.getCity() != null ? responseWeatherData.getCity().getSunrise() : null)
                            .sunset(responseWeatherData.getCity() != null ? responseWeatherData.getCity().getSunset() : null)
                            .build());
                }
                return list;
            }
        }
        return null;
    }
}
