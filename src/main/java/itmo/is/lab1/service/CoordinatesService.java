package itmo.is.lab1.service;

import itmo.is.lab1.DTO.model.data.CoordinatesDTO;
import itmo.is.lab1.dao.CoordinatesDAO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.model.data.Coordinates;
import itmo.is.lab1.objMapper.CoordinatesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CoordinatesService {

    @Autowired
    private CoordinatesDAO coordinatesDAO;

    @Autowired
    private CoordinatesMapper coordinatesMapper;

    public CoordinatesDTO createCoordinates(CoordinatesDTO coordinatesDTO) {
        Coordinates coordinates = coordinatesMapper.toEntity(coordinatesDTO);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        coordinates.setUser((User) authentication.getPrincipal());
        return coordinatesMapper.toDTO(coordinatesDAO.save(coordinates));
    }

    public CoordinatesDTO findCoordinatesById(Integer id) throws DbException, NotEnoughAccessLevelToData {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof User customUser) {
            Coordinates coordinates = coordinatesDAO.findById(id).orElseThrow(() ->
                    new DbException("No coordinates found with id = %d".formatted(id)));
            return coordinatesMapper.toDTO(coordinates);
        } else {
            throw new NotEnoughAccessLevelToData("No access rights");
        }
    }

    public void updateCoordinates(CoordinatesDTO coordinatesDTO) throws DbException, NotEnoughAccessLevelToData {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof User customUser) {
            Coordinates coordinates = coordinatesDAO.findById(coordinatesDTO.getId()).orElseThrow(() ->
                    new DbException("No coordinates found with id = %d".formatted(coordinatesDTO.getId())));
            if (!Objects.equals(coordinates.getUser().getId(), customUser.getId())) {
                throw new NotEnoughAccessLevelToData("Attempt to modify someone else's data");
            }
            coordinates.setX(coordinatesDTO.getX());
            coordinates.setY(coordinatesDTO.getY());
            coordinatesDAO.save(coordinates);
        } else {
            throw new NotEnoughAccessLevelToData("No access rights");
        }
    }

    public void deleteCoordinatesById(Integer id) throws DbException, NotEnoughAccessLevelToData {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof User customUser) {
            Coordinates coordinates = coordinatesDAO.findById(id).orElseThrow(() ->
                    new DbException("No coordinates found with id = %d".formatted(id)));
            if (!Objects.equals(coordinates.getUser().getId(), customUser.getId())) {
                throw new NotEnoughAccessLevelToData("Attempt to delete someone else's data");
            }
            coordinatesDAO.delete(coordinates);
        } else {
            throw new NotEnoughAccessLevelToData("No access rights");
        }
    }

    public Page<CoordinatesDTO> getNCoordinatesStartFromPage(Pageable pageable) {
        return coordinatesDAO.findAll(pageable).map(coordinatesMapper::toDTO);
    }
}
