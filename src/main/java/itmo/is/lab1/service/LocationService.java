package itmo.is.lab1.service;

import itmo.is.lab1.DTO.model.data.CoordinatesDTO;
import itmo.is.lab1.DTO.model.data.LocationDTO;
import itmo.is.lab1.dao.LocationDAO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.model.data.Location;
import itmo.is.lab1.objMapper.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

@Service
public class LocationService {

    @Autowired
    private LocationDAO locationDAO;

    @Autowired
    private LocationMapper locationMapper;

    public LocationDTO createLocation(LocationDTO locationDTO, User user) {
        locationDTO.setUserId(user.getId()); // Установка userId
        Location location = locationMapper.toEntity(locationDTO);
        location.setUser(user); // Привязываем пользователя к локации
        Location savedLocation = locationDAO.save(location);
        return locationMapper.toDTO(savedLocation);
    }

    public LocationDTO getLocationById(Integer id, User user) throws DbException, NotEnoughAccessLevelToData {
        Location location = locationDAO.findById(id).orElseThrow(() ->
                new DbException("Location not found with id = %d".formatted(id)));

        // Проверка доступа
//        if (!Objects.equals(location.getUser().getId(), user.getId())) {
//            throw new NotEnoughAccessLevelToData("Attempt to access someone else's data");
//        }

        return locationMapper.toDTO(location);
    }

    public void updateLocation(LocationDTO locationDTO, User user) throws NotEnoughAccessLevelToData, DbException {
        Location location = locationDAO.findById(locationDTO.getId()).orElseThrow(() ->
                new DbException("Location not found with id = %d".formatted(locationDTO.getId())));

        // Проверка доступа
        if (!Objects.equals(location.getUser().getId(), user.getId())) {
            throw new NotEnoughAccessLevelToData("Attempt to modify someone else's data");
        }

        // Обновление полей
        location.setX(locationDTO.getX());
        location.setY(locationDTO.getY());
        location.setName(locationDTO.getName());

        locationDAO.save(location);
    }

    public void deleteLocation(Integer id, User user) throws DbException, NotEnoughAccessLevelToData {
        Location location = locationDAO.findById(id).orElseThrow(() ->
                new DbException("Location not found with id = %d".formatted(id)));

        // Проверка доступа
        if (!Objects.equals(location.getUser().getId(), user.getId())) {
            throw new NotEnoughAccessLevelToData("Attempt to delete someone else's data");
        }

        locationDAO.delete(location);
    }
    public Page<LocationDTO> getNLocationStartFromPage(Pageable pageable) {
        return locationDAO.findAll(pageable).map(locationMapper::toDTO);
    }
}
