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
import pt.iade.unimanager.models.Unit;
import pt.iade.unimanager.models.exceptions.NotFoundException;
import pt.iade.unimanager.repositories.UnitRepository;

@RestController
@RequestMapping(path = "/api/units")
public class UnitController {
  private Logger logger = LoggerFactory.getLogger(StudentController.class);

  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Unit> getAllUnits() {
    logger.info("Sending all Units");
    return UnitRepository.getAllUnits();
  }

  @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Unit getUnitById(@PathVariable("id") int id) throws NotFoundException {
    logger.info("Sending Unit with ID " + id);
    Unit unit = UnitRepository.getUnitById(id);

    if (unit != null) {
      return unit;
    } else {
      throw new NotFoundException("" + id, "Unit", "ID");
    }
  }

  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response deleteUnitById(@PathVariable("id") int id) {
    logger.info("Deleting unit with ID " + id);
    if (UnitRepository.deleteUnitById(id)) {
      return new Response(id + " was deleted.", null);
    } else {
      return new Response(id + " not found.", null);
    }
  }

  @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public Unit addUnit(@RequestBody Unit unit) {
    logger.info("Adding unit " + unit.getName());
    UnitRepository.addUnit(unit);
    return unit;
  }

  @GetMapping(path = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Unit getUnitByName(@PathVariable("name") String name) throws NotFoundException {
    logger.info("Sending Unit with name " + name);
    Unit unit = UnitRepository.getUnitByName(name);

    if (unit != null) {
      return unit;
    } else {
      throw new NotFoundException(name, "Unit", "name");
    }
  }
}
