package itmo.is.lab1.service.model;

import itmo.is.lab1.DTO.model.data.PersonDTO;
import itmo.is.lab1.dao.LocationDAO;
import itmo.is.lab1.dao.PersonDAO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.model.data.Location;
import itmo.is.lab1.model.data.Person;
import itmo.is.lab1.mapper.PersonMapper;
import itmo.is.lab1.permission.PermissionChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private LocationDAO locationDAO;

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private PermissionChecker permissionChecker;

    public PersonDTO createPerson(PersonDTO personDTO, User user) throws DbException {
        Person person = personMapper.toEntity(personDTO);
        person.setUser(user); // Привязываем пользователя к сущности Person
        System.out.println(personDTO.getLocationId());
        Location location = locationDAO.findById(personDTO.getLocationId())
                .orElseThrow(() -> new DbException("Location not found with id: " + personDTO.getLocationId()));
        person.setLocation(location);

        Person savedPerson = personDAO.save(person);
        return personMapper.toDTO(savedPerson);
    }

    public PersonDTO getPersonById(Integer id) throws DbException, NotEnoughAccessLevelToData {
        Person person = personDAO.findById(id).orElseThrow(() ->
                new DbException("Person not found with id = %d".formatted(id)));

        return personMapper.toDTO(person);
    }

    public void updatePerson(PersonDTO personDTO, User user) throws NotEnoughAccessLevelToData, DbException {
        Person person = personDAO.findById(personDTO.getId()).orElseThrow(() ->
                new DbException("Person not found with id = %d".formatted(personDTO.getId())));

        // Проверка прав доступа
        permissionChecker.checkRUDPermission(person);
        // Обновление полей
        person.setEyeColor(personDTO.getEyeColor());
        person.setHairColor(personDTO.getHairColor());

        Location location = locationDAO.findById(personDTO.getLocationId())
                .orElseThrow(() -> new DbException("Address not found with id: " + personDTO.getLocationId()));
        person.setLocation(location);

        person.setLocation(location);
        person.setBirthday(personDTO.getBirthday());
        person.setNationality(personDTO.getNationality());

        personDAO.save(person);
    }

    public void deletePerson(Integer id, User user) throws DbException, NotEnoughAccessLevelToData {
        Person person = personDAO.findById(id).orElseThrow(() ->
                new DbException("Person not found with id = %d".formatted(id)));

        // Проверка прав доступа
        permissionChecker.checkRUDPermission(person);

        personDAO.delete(person);
    }
    public Page<PersonDTO> getNPeopleStartFromPage(Pageable pageable) {
        return personDAO.findAll(pageable).map(personMapper::toDTO);
    }
}
