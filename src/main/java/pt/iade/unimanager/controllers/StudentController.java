package pt.iade.unimanager.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.unimanager.models.Enrolment;
import pt.iade.unimanager.models.Response;
import pt.iade.unimanager.models.Student;
import pt.iade.unimanager.models.Unit;
import pt.iade.unimanager.models.exceptions.AlreadyExistsException;
import pt.iade.unimanager.models.exceptions.NotFoundException;
import pt.iade.unimanager.repositories.StudentRepository;
import pt.iade.unimanager.repositories.UnitRepository;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController {
  private Logger logger = LoggerFactory.getLogger(StudentController.class);

  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Student> getAllStudents() {
    logger.info("Sending all Students");
    return StudentRepository.getAllStudents();
  }

  @GetMapping(path = "/num/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Student getStudentByNumber(@PathVariable("number") int number) throws NotFoundException {
    logger.info("Sending Student with number " + number);
    Student student = StudentRepository.getStudentByNumber(number);

    if (student != null) {
      return student;
    } else {
      throw new NotFoundException("" + number, "Student", "number");
    }
  }

  @DeleteMapping(path = "/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
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

  @GetMapping(path = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Student getStudentByEmail(@PathVariable("email") String email) throws NotFoundException {
    logger.info("Sending Student with email " + email);
    Student student = StudentRepository.getStudentByEmail(email);

    if (student != null) {
      return student;
    } else {
      throw new NotFoundException("" + email, "Student", "email");
    }
  }

  @GetMapping(path = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Student getStudentByName(@PathVariable("name") String name) throws NotFoundException {
    logger.info("Sending Student with name " + name);
    Student student = StudentRepository.getStudentByName(name);

    if (student != null) {
      return student;
    } else {
      throw new NotFoundException(name, "Student", "name");
    }
  }

  @GetMapping(path = "/{number}/enrolments", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Enrolment> getEnrolments(@PathVariable("number") int number) throws NotFoundException {
    logger.info("Sending enrolments of student with number " + number);
    Student student = StudentRepository.getStudentByNumber(number);

    if (student != null) {
      return student.getEnrolments();
    } else {
      throw new NotFoundException("" + number, "Student", "number");
    }
  }

  @GetMapping(path = "/{number}/enrolments/{unitId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Enrolment getEnrolment(@PathVariable("number") int number, @PathVariable("unitId") int unitId)
      throws NotFoundException {
    logger.info("Sending enrolment with id " + unitId + " of student with number " + number);
    Student student = StudentRepository.getStudentByNumber(number);

    if (student != null) {
      Enrolment enr = student.getEnrolmentByUnitId(unitId);
      if (enr != null) {
        return enr;
      } else {
        throw new NotFoundException("" + unitId, "Unit", "id");
      }
    } else {
      throw new NotFoundException("" + number, "Student", "number");
    }
  }

  @PutMapping(path = "/{number}/enrolments/{unitId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Enrolment setGrade(@PathVariable("number") int number, @PathVariable("unitId") int unitId,
      @RequestBody double grade) throws NotFoundException {
    logger.info("Setting grade of enrolment with id " + unitId + " of student with number " + number);
    Student student = StudentRepository.getStudentByNumber(number);

    if (student != null) {
      Enrolment enr = student.getEnrolmentByUnitId(unitId);
      if (enr != null) {
        enr.setGrade(grade);
        return enr;
      } else {
        throw new NotFoundException("" + unitId, "Unit", "id");
      }
    } else {
      throw new NotFoundException("" + number, "Student", "number");
    }
  }

  @PostMapping(path = "{number}/enrolments", produces = MediaType.APPLICATION_JSON_VALUE)
  public Enrolment addEnrolment(@PathVariable("number") int number, @RequestBody int unitId)
      throws NotFoundException, AlreadyExistsException {
    logger.info("Enroling student with number " + number + " in unit with id " + unitId);
    Student student = StudentRepository.getStudentByNumber(number);
    if (student != null) {
      Unit unit = UnitRepository.getUnitById(unitId);
      if (unit != null) {
        if (student.getEnrolmentByUnitId(unitId) != null) {
          throw new AlreadyExistsException("" + unitId, "Unit", "id");
        } else {
          Enrolment enrolment = new Enrolment(student, unit, -1);
          student.enroll(enrolment);
          return enrolment;
        }
      } else {
        throw new NotFoundException("" + unitId, "Unit", "id");
      }
    } else {
      throw new NotFoundException("" + number, "Student", "number");
    }
  }

}
