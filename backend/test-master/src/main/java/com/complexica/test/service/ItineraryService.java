package com.complexica.test.service;

import com.complexica.test.model.dto.ItineraryDTO;
import com.complexica.test.model.dto.JourneyDTO;
import com.complexica.test.model.entities.Itinerary;
import com.complexica.test.model.entities.Journey;
import com.complexica.test.repository.AccountRepository;
import com.complexica.test.repository.ItineraryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ItineraryService {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<ItineraryDTO> getAll(final Long accountId) {
        return itineraryRepository.findAllItinerariesByAccountId(accountId).stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public ItineraryDTO get(Long id) throws Exception {
        return entityToDto(itineraryRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessException(String.format("No record found against id[%d]", id))));
    }

    public ItineraryDTO entityToDto(Itinerary itinerary) {
        return ItineraryDTO.builder()
                .id(itinerary.getId())
                .name(itinerary.getName())
                .journeyList(getJourneyList(itinerary.getJourneys()))
                .build();
    }

    private List<JourneyDTO> getJourneyList(Set<Journey> journeys) {
        List<JourneyDTO> journeyList = new ArrayList<>();
        for (Journey journey : journeys) {
            journeyList.add(JourneyDTO.builder()
                    .id(journey.getId())
                    .cityName(journey.getCityName())
                    .travelDate(journey.getTravelDate())
                    .build());
        }
        return journeyList;
    }

    public ItineraryDTO saveOrUpdate(ItineraryDTO itineraryDTO) {
        if (itineraryDTO.getId() != null) {
            log.info(String.format("Updating itinerary with id=%d.", itineraryDTO.getId()));
        } else {
            log.info("Creating new itinerary");
        }
        return entityToDto(saveOrUpdate(dtoToEntity(itineraryDTO)));
    }

    public void delete(Long id) {
        log.info(String.format("Deleting Itinerary having id=%d", id));
        cacheService.evict(Itinerary.class, id);
        itineraryRepository.deleteById(id);
    }

    private Itinerary saveOrUpdate(Itinerary itinerary) {
        itinerary.setAccount(accountRepository.findById(1L).get());//lets assume we have account in db
        Itinerary saved = itineraryRepository.save(itinerary);
        cacheService.evict(saved.getClass(), saved.getId());
        return saved;
    }

    protected Itinerary dtoToEntity(ItineraryDTO dto) {
        Itinerary itinerary;
        if (dto.getId() != null) {
            itinerary = itineraryRepository.findById(dto.getId()).orElse(new Itinerary());
        } else {
            itinerary = new Itinerary();
        }
        itinerary.setName(dto.getName());
        if (!CollectionUtils.isEmpty(dto.getJourneyList())) {
            itinerary.getJourneys().clear();
            for (JourneyDTO journeyDTO : dto.getJourneyList()) {
                Journey journey = new Journey();
                journey.setCityName(journeyDTO.getCityName());
                journey.setTravelDate(journeyDTO.getTravelDate());
                itinerary.getJourneys().add(journey);
            }
        }
        return itinerary;
    }
}
