package com.complexica.test.service;

import com.complexica.test.model.dto.ItineraryDTO;
import com.complexica.test.model.dto.JourneyDTO;
import com.complexica.test.model.entities.Itinerary;
import com.complexica.test.model.entities.Journey;
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

    public List<ItineraryDTO> getAll() {
        Iterable<Itinerary> list = itineraryRepository.findAll();
        if (list != null) {
            List<ItineraryDTO> itineraryDTOS = new ArrayList<>();
            list.forEach(i -> itineraryDTOS.add(entityToDto(i)));
            return itineraryDTOS;
        }
        return null;
    }

    public List<ItineraryDTO> getAll(final Long accountId) {
        if (accountId != null) {
            List<Itinerary> list = itineraryRepository.findAllItinerariesByAccountId(accountId);
            if (!CollectionUtils.isEmpty(list)) {
                return list.stream().map(this::entityToDto).collect(Collectors.toList());
            }
        } else {
            return getAll();
        }
        return null;
    }

    public Itinerary get(Long id) throws Exception {
        return itineraryRepository.findById(id).orElseThrow(() ->
                new IllegalAccessException(String.format("No record found against id[%d]", id)));
    }

    public ItineraryDTO getItineraryDTO(Long id) throws Exception {
        return entityToDto(get(id));
    }

    public ItineraryDTO entityToDto(Itinerary itinerary) {
        return ItineraryDTO.builder()
                .id(itinerary.getId())
                .name(itinerary.getName())
                .accountId(itinerary.getAccountId())
                .journeyList(getJourneyList(itinerary.getJourneys()))
                .build();
    }

    private List<JourneyDTO> getJourneyList(Set<Journey> journeys) {
        List<JourneyDTO> journeyList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(journeys)) {
            for (Journey journey : journeys) {
                journeyList.add(JourneyDTO.builder()
                        .id(journey.getId())
                        .cityName(journey.getCityName())
                        .travelDate(journey.getTravelDate())
                        .build());
            }
        }
        return journeyList;
    }

    public ItineraryDTO save(ItineraryDTO itineraryDTO) {
        return entityToDto(saveOrUpdate(dtoToEntity(itineraryDTO, new Itinerary())));
    }

    public ItineraryDTO update(Long id, ItineraryDTO itineraryDTO) throws Exception {
        return entityToDto(saveOrUpdate(dtoToEntity(itineraryDTO, get(id))));
    }

    public void delete(Long id) {
        log.info(String.format("Deleting Itinerary having id=%d", id));
        cacheService.evict(Itinerary.class, id);
        itineraryRepository.deleteById(id);
    }

    private Itinerary saveOrUpdate(Itinerary itinerary) {
        Itinerary saved = itineraryRepository.save(itinerary);
        cacheService.evict(saved.getClass(), saved.getId());
        return saved;
    }

    protected Itinerary dtoToEntity(ItineraryDTO dto, Itinerary itinerary) {
        itinerary.setName(dto.getName());
        itinerary.setAccountId(dto.getAccountId());
        if (!CollectionUtils.isEmpty(dto.getJourneyList())) {
            itinerary.getJourneys().clear();
            for (JourneyDTO journeyDTO : dto.getJourneyList()) {
                Journey journey = new Journey();
                journey.setCityName(journeyDTO.getCityName());
                journey.setTravelDate(journeyDTO.getTravelDate());
                journey.setItinerary(itinerary);
                itinerary.getJourneys().add(journey);
            }
        }
        return itinerary;
    }
}
