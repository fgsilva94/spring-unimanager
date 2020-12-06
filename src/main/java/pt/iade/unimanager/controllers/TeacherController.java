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
import pt.iade.unimanager.models.Teacher;
import pt.iade.unimanager.models.Unit;
import pt.iade.unimanager.models.exceptions.NotFoundException;
import pt.iade.unimanager.repositories.TeacherRepository;

@RestController
@RequestMapping(path = "/api/teachers")
public class TeacherController {
  private Logger logger = LoggerFactory.getLogger(TeacherController.class);

  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Teacher> getAllTeachers() {
    logger.info("Sending all Teachers");
    return TeacherRepository.getAllTeachers();
  }

  @GetMapping(path = "/num/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Teacher getTeacherByNumber(@PathVariable("number") int number) throws NotFoundException {
    logger.info("Sending Teacher with number " + number);
    Teacher teacher = TeacherRepository.getTeacherByNumber(number);

    if (teacher != null) {
      return teacher;
    } else {
      throw new NotFoundException("" + number, "Teacher", "number");
    }
  }

  @GetMapping(path = "/{number}/units")
  public List<Unit> getTeacherUnits(@PathVariable("number") int number) {
    logger.info("Sending T" + number + " Units");
    return TeacherRepository.getTeacherUnits(number);
  }

  @PostMapping(path = "/{number}/units", produces = MediaType.APPLICATION_JSON_VALUE)
  public void addUnit(@RequestBody Unit unit, @PathVariable("number") int number) {
    logger.info("Adding " + unit.getName() + " to T" + number);
    TeacherRepository.addUnit(unit, number);
  }

  @DeleteMapping(path = "/{number}/{unitId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response removeUnit(@PathVariable int unitId, @PathVariable("number") int number) {
    logger.info("Removing " + unitId + " to T" + number);
    if (TeacherRepository.removeUnit(unitId, number)) {
      return new Response("Unit with ID " + unitId + " was removed from T" + number, null);
    } else {
      return new Response("Unit with ID " + unitId + " wasn't removed from T" + number, null);
    }
  }
}
