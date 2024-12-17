package itmo.is.lab1.service.file;

import itmo.is.lab1.DTO.model.data.*;
import itmo.is.lab1.dao.*;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.mapper.AddressMapper;
import itmo.is.lab1.mapper.LocationMapper;
import itmo.is.lab1.mapper.PersonMapper;
import itmo.is.lab1.mapper.WorkerMapper;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.model.data.Coordinates;
import itmo.is.lab1.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {
    @Autowired
    private FileParser fileParser;

    @Autowired
    private AddressDAO addressDAO;

    @Autowired
    private CoordinatesDAO coordinatesDAO;

    @Autowired
    private LocationDAO locationDAO;

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private WorkerDAO workerDAO;
    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private WorkerMapper workerMapper;
    @Autowired
    private LocationService locationService;
    @Autowired
    private PersonService personService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private CoordinatesService coordinatesService;

    @Transactional(rollbackFor = {IOException.class, ClassNotFoundException.class, DbException.class})
    public void executeScript(MultipartFile file, User user) throws IOException, ClassNotFoundException, DbException {
        for (Object obj : fileParser.parseObjects(file)) {
            if (obj.getClass().equals(LocationDTO.class)) {
                locationService.createLocation((LocationDTO) obj, user);
            } else if (obj.getClass().equals(PersonDTO.class)) {
                personService.createPerson((PersonDTO) obj, user);
            } else if (obj.getClass().equals(AddressDTO.class)) {
                addressService.createAddress((AddressDTO) obj, user);
            } else if (obj.getClass().equals(WorkerDTO.class)) {
                workerService.createWorker((WorkerDTO) obj, user);
            } else if (obj.getClass().equals(OrganizationDTO.class)) {
                organizationService.createOrganization((OrganizationDTO) obj, user);
            } else if (obj.getClass().equals(CoordinatesDTO.class)) {
                coordinatesService.createCoordinates((CoordinatesDTO) obj, user);
            }
        }
    }

}
