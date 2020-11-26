package org.openweathermap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CityDTO {
    private int id;
    private String name;
    private String state;
    private String country;
    private Coord coord;
}
