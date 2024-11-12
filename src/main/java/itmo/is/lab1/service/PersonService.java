package itmo.is.lab1.service;

import itmo.is.lab1.DTO.model.data.LocationDTO;
import itmo.is.lab1.DTO.model.data.PersonDTO;
import itmo.is.lab1.dao.AddressDAO;
import itmo.is.lab1.dao.LocationDAO;
import itmo.is.lab1.dao.PersonDAO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.model.data.Address;
import itmo.is.lab1.model.data.Location;
import itmo.is.lab1.model.data.Person;
import itmo.is.lab1.objMapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PersonService {

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private LocationDAO locationDAO;

    @Autowired
    private PersonMapper personMapper;

    public PersonDTO createPerson(PersonDTO personDTO, User user) throws DbException {
        Person person = personMapper.toEntity(personDTO);
        person.setUser(user); // Привязываем пользователя к сущности Person

        Location location = locationDAO.findById(personDTO.getLocationId())
                .orElseThrow(() -> new DbException("Address not found with id: " + personDTO.getLocationId()));
        person.setLocation(location);

        Person savedPerson = personDAO.save(person);
        return personMapper.toDTO(savedPerson);
    }

    public PersonDTO getPersonById(Integer id, User user) throws DbException, NotEnoughAccessLevelToData {
        Person person = personDAO.findById(id).orElseThrow(() ->
                new DbException("Person not found with id = %d".formatted(id)));

        // Проверка прав доступа
        if (!Objects.equals(person.getUser().getId(), user.getId())) {
            throw new NotEnoughAccessLevelToData("Attempt to access someone else's data");
        }

        return personMapper.toDTO(person);
    }

    public void updatePerson(PersonDTO personDTO, User user) throws NotEnoughAccessLevelToData, DbException {
        Person person = personDAO.findById(personDTO.getId()).orElseThrow(() ->
                new DbException("Person not found with id = %d".formatted(personDTO.getId())));

        // Проверка прав доступа
        if (!Objects.equals(person.getUser().getId(), user.getId())) {
            throw new NotEnoughAccessLevelToData("Attempt to modify someone else's data");
        }

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
        if (!Objects.equals(person.getUser().getId(), user.getId())) {
            throw new NotEnoughAccessLevelToData("Attempt to delete someone else's data");
        }

        personDAO.delete(person);
    }
}
