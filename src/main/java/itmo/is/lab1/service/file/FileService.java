package itmo.is.lab1.service.file;

import itmo.is.lab1.dao.*;
import itmo.is.lab1.model.data.Address;
import itmo.is.lab1.model.data.Location;
import itmo.is.lab1.model.data.Person;
import itmo.is.lab1.model.data.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

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

    public void executeScript(MultipartFile file) throws IOException, ClassNotFoundException {
        for (Object obj : fileParser.parseObjects(file)) {
            if (obj.getClass().equals(Location.class)) {
                locationDAO.save((Location) obj);
            } else if (obj.getClass().equals(Person.class)) {
                personDAO.save((Person) obj);
            } else if (obj.getClass().equals(Address.class)) {
                addressDAO.save((Address) obj);
            } else if (obj.getClass().equals(Worker.class)) {
                workerDAO.save((Worker) obj);
            }

        }
    }
}
