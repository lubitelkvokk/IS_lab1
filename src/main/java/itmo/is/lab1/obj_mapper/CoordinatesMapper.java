package itmo.is.lab1.obj_mapper;

import itmo.is.lab1.DTO.model.data.CoordinatesDTO;
import itmo.is.lab1.model.data.Coordinates;

public class CoordinatesMapper implements GenericMapper<Coordinates, CoordinatesDTO> {

    @Override
    public CoordinatesDTO toDTO(Coordinates coordinates) {
        if (coordinates == null) {
            return null;
        }
        return new CoordinatesDTO(coordinates.getX(), coordinates.getY());
    }

    @Override
    public Coordinates toEntity(CoordinatesDTO coordinatesDTO) {
        if (coordinatesDTO == null) {
            return null;
        }
        Coordinates coordinates = new Coordinates();
        coordinates.setX(coordinatesDTO.getX());
        coordinates.setY(coordinatesDTO.getY());
        return coordinates;
    }
}
