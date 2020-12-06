package pt.iade.unimanager.models;

import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Student extends Person {
  private static int nextNumber = 0;
  private int number;

  @JsonIgnore
  private ArrayList<Enrolment> enrolments;

  public Student(String name, LocalDate birthDate, char gender) {
    super(name, birthDate, gender);
    this.number = nextNumber;
    nextNumber++;
    enrolments = new ArrayList<>();
  }

  public static int getNextNumber() {
    return nextNumber;
  }

  public int getNumber() {
    return number;
  }

  public ArrayList<Enrolment> getEnrolments() {
    return enrolments;
  }

  public Enrolment getEnrolmentByUnitId(int unitId) {
    for (Enrolment enr : enrolments) {
      if (enr.getUnit().getId() == unitId) {
        return enr;
      }
    }

    return null;
  }

  public void enroll(Enrolment enrolment) {
    enrolments.add(enrolment);
    enrolment.getUnit().getEnrolments().add(enrolment);
  }

  @Override
  public String getReference() {
    return "S" + number;
  }
}
