package itmo.is.lab1.obj_mapper;

import itmo.is.lab1.DTO.model.data.LocationDTO;
import itmo.is.lab1.model.data.Location;

public class LocationMapper implements GenericMapper<Location, LocationDTO> {

    @Override
    public LocationDTO toDTO(Location location) {
        if (location == null) {
            return null;
        }
        return new LocationDTO(
                location.getX(),
                location.getY(),
                location.getName()
        );
    }

    @Override
    public Location toEntity(LocationDTO locationDTO) {
        if (locationDTO == null) {
            return null;
        }
        Location location = new Location();
        location.setX(locationDTO.getX());
        location.setY(locationDTO.getY());
        location.setName(locationDTO.getName());
        return location;
    }
}
