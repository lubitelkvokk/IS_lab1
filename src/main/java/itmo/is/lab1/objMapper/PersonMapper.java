package itmo.is.lab1.objMapper;

import itmo.is.lab1.DTO.model.data.PersonDTO;
import itmo.is.lab1.model.data.Person;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Component
public class PersonMapper implements GenericMapper<Person, PersonDTO> {

    @Override
    public PersonDTO toDTO(Person person) {
        if (person == null) {
            return null;
        }
        PersonDTO personDTO = new PersonDTO();
        personDTO.setEyeColor(person.getEyeColor());
        personDTO.setHairColor(person.getHairColor());
        personDTO.setLocation(person.getLocation());
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
        person.setEyeColor(personDTO.getEyeColor());
        person.setHairColor(personDTO.getHairColor());
        person.setLocation(personDTO.getLocation());
        person.setBirthday(personDTO.getBirthday());
        person.setNationality(personDTO.getNationality());
        return person;
    }
}
