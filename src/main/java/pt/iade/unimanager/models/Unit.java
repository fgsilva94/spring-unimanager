package pt.iade.unimanager.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Unit {
  private static int nextId = 0;
  private int id;
  private String name;
  private int credits;

  @JsonIgnore
  private ArrayList<Enrolment> enrolments;

  public Unit(String name, int credits) {
    this.id = nextId;
    this.name = name;
    this.credits = credits;
    nextId++;
    enrolments = new ArrayList<>();
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
}
