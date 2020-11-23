package org.openweathermap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Weather {
    public int id;
    public String main;
    public String description;
    public String icon;
}
