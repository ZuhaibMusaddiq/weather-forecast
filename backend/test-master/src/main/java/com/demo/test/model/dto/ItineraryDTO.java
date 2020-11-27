package com.demo.test.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = {"id", "name"} )
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItineraryDTO {
    List<JourneyDTO> journeyList;
    private Long id;
    private String name;
    private String description;
    private Long accountId;
}
