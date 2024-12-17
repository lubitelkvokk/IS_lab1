package itmo.is.lab1.mapper;

import itmo.is.lab1.DTO.model.data.PersonDTO;
import itmo.is.lab1.model.data.Location;
import itmo.is.lab1.model.data.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper implements GenericMapper<Person, PersonDTO> {

    @Override
    public PersonDTO toDTO(Person person) {
        if (person == null) {
            return null;
        }
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setUserId(person.getUser().getId());
        personDTO.setEyeColor(person.getEyeColor());
        personDTO.setHairColor(person.getHairColor());
        personDTO.setLocationId(person.getLocation().getId());
        personDTO.setBirthday(person.getBirthday());
        personDTO.setNationality(person.getNationality());
        return personDTO;
    }

    @Override
    public Person toEntity(PersonDTO personDTO) {
        if (personDTO == null) {
            return null;
        }
        Person person = new Person();
        person.setId(personDTO.getId());
        person.setEyeColor(personDTO.getEyeColor());
        person.setHairColor(personDTO.getHairColor());
        Location location = new Location();
        location.setId(personDTO.getId());
        person.setLocation(location);
        person.setBirthday(personDTO.getBirthday());
        person.setNationality(personDTO.getNationality());
        return person;
    }
}
