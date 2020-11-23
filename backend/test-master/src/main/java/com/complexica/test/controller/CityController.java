package com.complexica.test.controller;

import com.complexica.test.model.dto.ItineraryDTO;
import com.complexica.test.service.ItineraryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.openweathermap.CityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/cities")
public class CityController { //extra work, to get list of cities for cities dropdown

    @GetMapping("/")
    public @ResponseBody
    List<String> getCities() throws IOException {
        File resource = new ClassPathResource("test.json").getFile();
        String json = new String(Files.readAllBytes(resource.toPath()));
        ObjectMapper objectMapper = new ObjectMapper();
        List<CityDTO> cityDTOs = objectMapper.readValue(json, List.class);
        List<String> cities = new ArrayList<>();
        for (CityDTO cityDTO: cityDTOs){
            cities.add(cityDTO.name);
        }
        return cities;
    }
}
