package com.complexica.test.controller;

import com.complexica.test.model.dto.ItineraryDTO;
import com.complexica.test.service.ItineraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

    @GetMapping("/itinerary")
    public @ResponseBody
    ItineraryDTO getItinerary(@RequestParam Long id) throws Exception {
        return itineraryService.get(id);
    }

    @PostMapping("/itinerary")
    public @ResponseBody
    ItineraryDTO updateItinerary(@RequestBody ItineraryDTO itineraryDTO) {
        return itineraryService.saveOrUpdate(itineraryDTO);
    }

    @DeleteMapping("/itinerary")
    public void deleteItinerary(@RequestParam Long id) throws IllegalAccessException {
        itineraryService.delete(id);
    }

    @GetMapping("/user-itinerary")
    public @ResponseBody
    List<ItineraryDTO> getUserItineraries() {
        return itineraryService.getAll(new Long(1L));//so far we assume we have 1 user
    }
}
