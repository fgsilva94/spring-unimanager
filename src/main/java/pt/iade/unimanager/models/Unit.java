package pt.iade.unimanager.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Unit {
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
}
