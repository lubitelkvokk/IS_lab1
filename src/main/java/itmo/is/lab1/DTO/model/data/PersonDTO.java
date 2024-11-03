package itmo.is.lab1.DTO.model.data;

import itmo.is.lab1.model.data.Color;
import itmo.is.lab1.model.data.Country;
import itmo.is.lab1.model.data.Location;
import lombok.Data;

@Data
public class PersonDTO {
    private Color eyeColor; //Поле не может быть null
    private Color hairColor; //Поле не может быть null
    private Location location; //Поле может быть null
    private java.time.ZonedDateTime birthday; //Поле не может быть null
    private Country nationality; //Поле не может быть null

}
