package pt.iade.unimanager.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Unit implements Statistical {
  private static int nextId = 0;
  private int id;
  private String name;
  private int credits;

  @JsonIgnore
  private ArrayList<Enrolment> enrolments;

  @JsonIgnore
  private ArrayList<Reserve> reserves;

  public Unit(String name, int credits) {
    this.id = nextId;
    this.name = name;
    this.credits = credits;
    nextId++;
    enrolments = new ArrayList<>();
    reserves = new ArrayList<>();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getCredits() {
    return credits;
  }

  public ArrayList<Enrolment> getEnrolments() {
    return enrolments;
  }

  public ArrayList<Reserve> getReserves() {
    return reserves;
  }

  public List<Reserve> getReservesByRoomDesignation(String designation) {
    List<Reserve> newReserves = reserves;
    newReserves.removeIf(r -> !(r.getRoom().getDesignation().toLowerCase().equals(designation.toLowerCase())));

    return newReserves;
  }

  public void addReservation(Reserve reserve) {
    reserves.add(reserve);
    reserve.getRoom().getReserves().add(reserve);
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
