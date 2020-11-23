package com.complexica.test.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "journey")
public class Journey implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50, nullable = false)
    private String cityName;

    @Column(name = "travel_date", nullable = false)
    private Date travelDate;

    @ManyToOne
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey that = (Journey) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cityName, that.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cityName);
    }

    @Override
    public String toString() {
        return "Itinerary{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
