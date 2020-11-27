package com.demo.test.repository;

import com.demo.test.model.entities.Itinerary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ItineraryRepository extends CrudRepository<Itinerary, Long> {

    @Query(value = "select i from Itinerary i where i.accountId = :accountId")
    List<Itinerary> findAllItinerariesByAccountId(@Param("accountId") final Long accountId);
}
