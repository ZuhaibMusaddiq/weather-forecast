package com.demo.test.model.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString(of = {"id", "cityName", "travelDate"})
@EqualsAndHashCode(of = {"id", "cityName", "travelDate"})
@Table(name = "journey")
public class Journey implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "city_name", length = 50, nullable = false)
    private String cityName;

    @Column(name = "travel_date", nullable = false)
    private Date travelDate;

    @ManyToOne
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;
}
