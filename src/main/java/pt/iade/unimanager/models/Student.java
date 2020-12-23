package pt.iade.unimanager.models;

import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Student extends Person implements Statistical {
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

  // @JsonIgnore
  @Override
  public String getReference() {
    return "S" + number;
  }

  // @JsonIgnore
  @Override
  public double getAverage() {
    double sum = 0;
    int n = 0;
    for (Enrolment enr : enrolments) {
      if (enr.getGrade() > 0) {
        n++;
        sum += enr.getGrade();
      }
    }
    return sum / n;
  }

  // @JsonIgnore
  @Override
  public double getMax() {
    double max = 0;
    for (Enrolment enr : enrolments) {
      if (enr.getGrade() > max) {
        max = enr.getGrade();
      }
    }
    return max;
  }

  // @JsonIgnore
  @Override
  public double getMin() {
    double min = Double.MAX_VALUE;
    for (Enrolment enr : enrolments) {
      if (enr.getGrade() > 0 && enr.getGrade() < min) {
        min = enr.getGrade();
      }
    }
    return min;
  }

  // @JsonIgnore
  @Override
  public HistogramSlot[] getHistogram(int nSlots) {
    double slotSize = 20 / nSlots;
    HistogramSlot[] hSlots = new HistogramSlot[nSlots];

    for (int i = 0; i < hSlots.length; i++) {
      double start = slotSize * i;
      double end = start + slotSize;
      double value = 0;

      for (Enrolment enr : enrolments) {
        if (enr.getGrade() > start && enr.getGrade() <= end) {
          value++;
        }
      }

      hSlots[i] = new HistogramSlot(start, end, value);
    }

    return hSlots;
  }
}
