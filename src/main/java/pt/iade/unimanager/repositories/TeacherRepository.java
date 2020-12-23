package pt.iade.unimanager.repositories;

import java.time.LocalDate;
import java.util.ArrayList;

import pt.iade.unimanager.models.Teacher;
import pt.iade.unimanager.models.Unit;

public class TeacherRepository {
  private static ArrayList<Teacher> teachers = new ArrayList<>();

  public static void populate() {
    teachers.add(new Teacher("Miguel", LocalDate.parse("1990-05-12"), 'M'));
    teachers.add(new Teacher("Jacinto", LocalDate.parse("1990-05-12"), 'M'));
    teachers.add(new Teacher("Alexandra", LocalDate.parse("1990-05-12"), 'M'));

    teachers.get(0).getUnits().add(UnitRepository.getUnitById(0));
    teachers.get(1).getUnits().add(UnitRepository.getUnitById(1));
    teachers.get(2).getUnits().add(UnitRepository.getUnitById(4));
  }

  public static ArrayList<Teacher> getAllTeachers() {
    return teachers;
  }

  public static Teacher getTeacherByNumber(int number) {
    for (Teacher t : teachers) {
      if (t.getMecNumber() == number) {
        return t;
      }
    }

    return null;
  }

  public static ArrayList<Unit> getTeacherUnits(int number) {
    for (Teacher t : teachers) {
      if (t.getMecNumber() == number) {
        return t.getUnits();
      }
    }

    return null;
  }

  public static void addUnit(Unit unit, int number) {
    for (Teacher t : teachers) {
      if (t.getMecNumber() == number) {
        t.getUnits().add(unit);
      }
    }
  }

  public static boolean removeUnit(int unitId, int number) {
    for (Teacher t : teachers) {
      if (t.getMecNumber() == number) {
        return t.getUnits().removeIf(x -> x.getId() == unitId);
      }
    }

    return false;
  }
}
