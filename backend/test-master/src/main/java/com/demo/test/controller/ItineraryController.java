package com.demo.test.controller;

import com.demo.test.model.dto.ItineraryDTO;
import com.demo.test.service.ItineraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

    @GetMapping("/itinerary")
    public ResponseEntity<List<ItineraryDTO>> getItineraries(@RequestParam(required = false) Long accountId) {
        try {
            List<ItineraryDTO> itineraryDTOList = itineraryService.getAll(accountId);

            if (CollectionUtils.isEmpty(itineraryDTOList)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(itineraryDTOList, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/itinerary/{id}")
    public ResponseEntity<ItineraryDTO> getItineraryById(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(itineraryService.getItineraryDTO(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/itinerary")
    public ResponseEntity<ItineraryDTO> createItinerary(@RequestBody ItineraryDTO itineraryDTO) {
        try {
            ItineraryDTO _itinerary = itineraryService.save(itineraryDTO);
            return new ResponseEntity<>(_itinerary, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/itinerary/{id}")
    public ResponseEntity<ItineraryDTO> updateItinerary(@PathVariable("id") long id, @RequestBody ItineraryDTO itineraryDTO) {
        try {
            ItineraryDTO _itinerary = itineraryService.update(id, itineraryDTO);
            return new ResponseEntity<>(_itinerary, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/itinerary/{id}")
    public ResponseEntity<HttpStatus> deleteItinerary(@PathVariable("id") long id) {
        try {
            itineraryService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
