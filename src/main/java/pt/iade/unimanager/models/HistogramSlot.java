package pt.iade.unimanager.models;

public class HistogramSlot {
  private double start;
  private double end;
  private double value;

  public HistogramSlot(double start, double end, double value) {
    this.start = start;
    this.end = end;
    this.value = value;
  }

  public double getStart() {
    return start;
  }

  public void setStart(double start) {
    this.start = start;
  }

  public double getEnd() {
    return end;
  }

  public void setEnd(double end) {
    this.end = end;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }
}
