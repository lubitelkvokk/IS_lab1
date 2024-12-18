package itmo.is.lab1.service.file;

import itmo.is.lab1.DTO.model.data.*;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.ImportFormatException;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.service.model.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class FileService {
    @Autowired
    private FileParser fileParser;

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
    @Autowired
    private ValidationAutoConfiguration validationAutoConfiguration;

    /**
     * @param file
     * @param user
     * @return count added object
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws DbException
     */
    @Transactional(rollbackFor = {IOException.class, ClassNotFoundException.class, DbException.class})
    public int executeScript(MultipartFile file, User user) throws IOException, ClassNotFoundException, DbException, ImportFormatException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        List<Object> result = fileParser.parseObjects(file);
        for (Object obj : result) {
            Set<ConstraintViolation<Object>> cvs = validator.validate(obj);
            if (!cvs.isEmpty()) {
                throw new ImportFormatException(cvs.iterator().next().getMessage());
            }
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

        return result.size();

    }

}
