package itmo.is.lab1.objMapper;

import itmo.is.lab1.DTO.model.data.CoordinatesDTO;
import itmo.is.lab1.model.data.Coordinates;
import org.springframework.stereotype.Component;

@Component
public class CoordinatesMapper implements GenericMapper<Coordinates, CoordinatesDTO> {

    @Override
    public CoordinatesDTO toDTO(Coordinates coordinates) {
        if (coordinates == null) {
            return null;
        }
        return new CoordinatesDTO(coordinates.getId(), coordinates.getX(), coordinates.getY());
    }

    @Override
    public Coordinates toEntity(CoordinatesDTO coordinatesDTO) {
        if (coordinatesDTO == null) {
            return null;
        }
        Coordinates coordinates = new Coordinates(coordinatesDTO.getId(), coordinatesDTO.getX(), coordinatesDTO.getY());
        return coordinates;
    }
}
