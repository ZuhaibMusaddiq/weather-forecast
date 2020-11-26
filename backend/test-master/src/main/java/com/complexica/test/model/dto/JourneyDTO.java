package com.complexica.test.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "cityName", "travelDate"})
@EqualsAndHashCode(of = {"id", "cityName", "travelDate"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JourneyDTO {
    private Long id;
    private String cityName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date travelDate;
}
