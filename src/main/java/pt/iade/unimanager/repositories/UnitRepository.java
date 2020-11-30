package pt.iade.unimanager.repositories;

import java.util.ArrayList;

import pt.iade.unimanager.models.Unit;

public class UnitRepository {
  private static ArrayList<Unit> units = new ArrayList<Unit>();

  public static void populate() {
    units.add(new Unit("POO", 6));
    units.add(new Unit("BD", 6));
    units.add(new Unit("SO", 6));
    units.add(new Unit("MD", 6));
    units.add(new Unit("CC", 3));
    units.add(new Unit("TI1", 3));
  }

  public static ArrayList<Unit> getAllUnits() {
    return units;
  }

  public static Unit getUnitById(int id) {
    for (Unit x : units) {
      if (x.getId() == id) {
        return x;
      }
    }

    return null;
  }

  public static boolean deleteUnitById(int id) {
    return units.removeIf(x -> x.getId() == id);
  }

  public static void addUnit(Unit unit) {
    units.add(unit);
  }

  public static Unit getUnitByName(String name) {
    for (Unit x : units) {
      if (x.getName().toLowerCase().equals(name.toLowerCase())) {
        return x;
      }
    }

    return null;
  }
}
