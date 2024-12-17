package itmo.is.lab1.mapper;

import itmo.is.lab1.DTO.model.data.LocationDTO;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.model.data.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper implements GenericMapper<Location, LocationDTO> {

    @Override
    public LocationDTO toDTO(Location location) {
        if (location == null) {
            return null;
        }
        return new LocationDTO(
                location.getId(),
                location.getUser().getId(),
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
        User user = new User();
        user.setId(locationDTO.getUserId());
        location.setX(locationDTO.getX());
        location.setY(locationDTO.getY());
        location.setName(locationDTO.getName());
        return location;
    }
}
