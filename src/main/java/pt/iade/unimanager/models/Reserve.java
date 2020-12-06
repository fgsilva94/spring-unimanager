package pt.iade.unimanager.models;

import java.time.LocalDateTime;

public class Reserve {
  private Room room;
  private Unit unit;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  public Reserve(Room room, Unit unit, LocalDateTime startTime, LocalDateTime endTime) {
    this.room = room;
    this.unit = unit;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }
}
