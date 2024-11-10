package itmo.is.lab1.service;

import itmo.is.lab1.DTO.model.data.CoordinatesDTO;
import itmo.is.lab1.dao.CoordinatesDAO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.objMapper.CoordinatesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoordinatesService {

    @Autowired
    private CoordinatesDAO coordinatesDAO;

    @Autowired
    private CoordinatesMapper coordinatesMapper;


    public CoordinatesDTO createCoordinates(CoordinatesDTO coordinatesDTO) {
        return coordinatesMapper.toDTO(coordinatesDAO.save(coordinatesMapper.toEntity(coordinatesDTO)));

    }

    public CoordinatesDTO findCoordinatesById(Integer id) {
        return coordinatesMapper.toDTO(coordinatesDAO.findById(id).orElseThrow());
    }

    public boolean isExistCoordinates(CoordinatesDTO coordinatesDTO) {
        return coordinatesDAO.findById(coordinatesDTO.getId()) != null;
    }

    public void updateCoordinatesById(CoordinatesDTO coordinatesDTO) throws DbException {
        coordinatesDAO.updateOrInsert(coordinatesMapper.toEntity(coordinatesDTO));
    }
}
