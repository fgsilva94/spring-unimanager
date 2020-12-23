package pt.iade.unimanager.models;

public class GroupResult {
  private Object object;
  private double average;
  private int size;

  public GroupResult(Object object, double average, int size) {
    this.object = object;
    this.average = average;
    this.size = size;
  }

  public Object getObject() {
    return object;
  }

  public void setObject(Object object) {
    this.object = object;
  }

  public double getAverage() {
    return average;
  }

  public void setAverage(double average) {
    this.average = average;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }
}
