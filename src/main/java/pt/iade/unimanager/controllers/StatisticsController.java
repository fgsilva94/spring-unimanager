package pt.iade.unimanager.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.unimanager.models.Enrolment;
import pt.iade.unimanager.models.GroupResult;
import pt.iade.unimanager.models.HistogramSlot;
import pt.iade.unimanager.models.Statistical;
import pt.iade.unimanager.models.StatisticsResult;
import pt.iade.unimanager.models.Unit;
import pt.iade.unimanager.repositories.StudentRepository;
import pt.iade.unimanager.repositories.TeacherRepository;
import pt.iade.unimanager.repositories.UnitRepository;

@RestController
@RequestMapping(path = "/api/statistics")
public class StatisticsController {
  private Logger logger = LoggerFactory.getLogger(StatisticsController.class);
  private static final int NHISTSLOTS = 5;

  @GetMapping(path = "/histogram/{type}/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
  public HistogramSlot[] getHistogram(@PathVariable("type") String type, @PathVariable("number") int number) {
    logger.info("Obtaining histogram info");
    Statistical stats;

    if (type.equalsIgnoreCase("student")) {
      stats = StudentRepository.getStudentByNumber(number);
    } else {
      stats = UnitRepository.getUnitById(number);
    }

    return stats.getHistogram(NHISTSLOTS);
  }

  @GetMapping(path = "/{type}/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
  public StatisticsResult getStatistics(@PathVariable("type") String type, @PathVariable("number") int number) {
    logger.info("Obtaining statistics");
    Statistical stats;

    if (type.equalsIgnoreCase("student")) {
      stats = StudentRepository.getStudentByNumber(number);
    } else {
      stats = UnitRepository.getUnitById(number);
    }

    return new StatisticsResult(stats.getAverage(), stats.getMax(), stats.getMin(), stats.getRange());
  }

  @GetMapping(path = "/group/average/{type}/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
  public GroupResult getGroupAverage(@PathVariable("type") String type, @PathVariable("number") int number) {
    logger.info("Obtaining group average");
    ArrayList<Statistical> stats = new ArrayList<>();

    if (type.equalsIgnoreCase("teacher")) {
      stats.addAll(TeacherRepository.getTeacherByNumber(number).getUnits());
    } else {
      Unit unit = UnitRepository.getUnitById(number);
      for (Enrolment enr : unit.getEnrolments()) {
        stats.add(enr.getStudent());
      }
    }

    return new GroupResult(stats, Statistical.getGroupAverage(stats), stats.size());
  }
}
