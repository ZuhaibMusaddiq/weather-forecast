package com.complexica.test.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "id", "cityName"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JourneyDTO {
    private Long id;
    private String cityName;
    private Date travelDate;
}
