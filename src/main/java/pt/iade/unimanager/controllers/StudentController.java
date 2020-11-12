package pt.iade.unimanager.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.unimanager.models.Response;
import pt.iade.unimanager.models.Student;
import pt.iade.unimanager.models.exceptions.NotFoundException;
import pt.iade.unimanager.repositories.StudentRepository;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController {
  private Logger logger = LoggerFactory.getLogger(StudentController.class);

  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Student> getAllStudents() {
    logger.info("Sending all Students");
    return StudentRepository.getAllStudents();
  }

  @GetMapping(path = "{number}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Student getStudentByNumber(@PathVariable("number") int number) throws NotFoundException {
    logger.info("Sending Student with number " + number);
    Student student = StudentRepository.getStudentByNumber(number);

    if (student != null) {
      return student;
    } else {
      throw new NotFoundException("" + number, "Student", "number");
    }
  }

  @DeleteMapping(path = "{number}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response deleteStudentByNumber(@PathVariable("number") int number) {
    logger.info("Deleting student with number " + number);
    if (StudentRepository.deleteStudentByNumber(number)) {
      return new Response(number + " was deleted.", null);
    } else {
      return new Response(number + " not found.", null);
    }
  }

  @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public Student addStudent(@RequestBody Student student) {
    logger.info("Adding student " + student.getName());
    StudentRepository.addStudent(student);
    return student;
  }
}
