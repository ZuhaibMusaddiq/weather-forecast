package com.complexica.test.model.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = {"id", "name"})
@Table(name = "itinerary")
@Cacheable
public class Itinerary implements Serializable {
    private static final long serialVersionUID = 1L;
    @OneToMany(mappedBy = "itinerary", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final Set<Journey> journeys = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;

    @Column(name = "account_id", nullable = true)
    private Long accountId;
}
