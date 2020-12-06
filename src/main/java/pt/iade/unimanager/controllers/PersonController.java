package pt.iade.unimanager.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.unimanager.models.Person;
import pt.iade.unimanager.models.exceptions.NotFoundException;
import pt.iade.unimanager.repositories.StudentRepository;
import pt.iade.unimanager.repositories.TeacherRepository;

@RestController
@RequestMapping(path = "/api/persons")
public class PersonController {
  private Logger logger = LoggerFactory.getLogger(PersonController.class);

  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Person> getPersons() {
    logger.info("Sending all Persons");
    return getAllPersons();
  }

  @GetMapping(path = "/ref/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Person getPersonByReference(@PathVariable("reference") String reference) throws NotFoundException {
    logger.info("Sending Persons with reference " + reference);
    ArrayList<Person> persons = getAllPersons();
    for (Person p : persons) {
      if (p.getReference().toLowerCase().equals(reference.toLowerCase())) {
        return p;
      }
    }

    throw new NotFoundException(reference, "Person", "Reference");
  }

  @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Person> getPersonByNameOrEmail(@RequestParam(value = "name", defaultValue = "") String name,
      @RequestParam(value = "email", defaultValue = "") String email) {
    logger.info("Sending all Persons with name " + name);
    ArrayList<Person> persons = getAllPersons();

    persons.removeIf(p -> !(p.getName().toLowerCase().contains(name.toLowerCase())
        && p.getEmail().toLowerCase().contains(email.toLowerCase())));

    return persons;
  }

  private ArrayList<Person> getAllPersons() {
    ArrayList<Person> persons = new ArrayList<>();
    persons.addAll(StudentRepository.getAllStudents());
    persons.addAll(TeacherRepository.getAllTeachers());
    return persons;
  }
}
