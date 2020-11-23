package org.openweathermap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CityDTO {
    public int id;
    public String name;
    public String state;
    public String country;
    public Coord coord;
}
