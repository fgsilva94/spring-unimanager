package pt.iade.unimanager.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Room {
  protected String designation;
  protected int capacity;

  @JsonIgnore
  private ArrayList<Reserve> reserves;

  public Room(String designation, int capacity) {
    this.designation = designation;
    this.capacity = capacity;
    reserves = new ArrayList<>();
  }

  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public ArrayList<Reserve> getReserves() {
    return reserves;
  }
}
