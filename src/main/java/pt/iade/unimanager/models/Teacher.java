package pt.iade.unimanager.models;

import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Teacher extends Person {
  private static int nextNumber = 0;
  private int mecNumber;

  @JsonIgnore
  private ArrayList<Unit> units;

  public Teacher(String name, LocalDate birthDate, char gender) {
    super(name, birthDate, gender);
    this.mecNumber = nextNumber;
    nextNumber++;
    units = new ArrayList<>();
  }

  public static int getNextNumber() {
    return nextNumber;
  }

  public int getMecNumber() {
    return mecNumber;
  }

  public ArrayList<Unit> getUnits() {
    return units;
  }

  @Override
  public String getName() {
    return "Professor " + name;
  }

  @Override
  public String getReference() {
    return "T" + mecNumber;
  }

}
