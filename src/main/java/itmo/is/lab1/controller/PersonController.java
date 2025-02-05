package itmo.is.lab1.controller;

import io.swagger.v3.oas.annotations.Operation;
import itmo.is.lab1.DTO.model.data.PersonDTO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.service.model.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@Validated
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    @Operation(description = "Создает новую запись о человеке")
    public ResponseEntity<PersonDTO> createPerson(@AuthenticationPrincipal User user, @Valid @RequestBody PersonDTO personDTO) throws DbException {
        PersonDTO createdPerson = personService.createPerson(personDTO, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }

    @GetMapping
    @Operation(description = "Принимает параметры page, size для выполнения пагинации")
    public ResponseEntity<Page<PersonDTO>> getAllPeople(Pageable pageable) {
        Page<PersonDTO> people = personService.
                getNPeopleStartFromPage(pageable);
        return ResponseEntity.ok(people);
    }

    @GetMapping("/{id}")
    @Operation(description = "Получает информацию о человеке по id")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Integer id) throws DbException, NotEnoughAccessLevelToData {
        PersonDTO personDTO = personService.getPersonById(id);
        return ResponseEntity.status(HttpStatus.OK).body(personDTO);
    }

    @PutMapping
    @Operation(description = "Обновляет информацию о человеке")
    public ResponseEntity<String> updatePerson(@AuthenticationPrincipal User user, @Valid @RequestBody PersonDTO personDTO) throws NotEnoughAccessLevelToData, DbException {
        System.out.println(user.getUsername());
        personService.updatePerson(personDTO, user);
        return ResponseEntity.status(HttpStatus.OK).body("Person updated successfully");
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаляет запись о человеке по id")
    public ResponseEntity<String> deletePerson(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotEnoughAccessLevelToData, DbException {
        personService.deletePerson(id, user);
        return ResponseEntity.status(HttpStatus.OK).body("Person deleted successfully");
    }
}
