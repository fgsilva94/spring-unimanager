package pt.iade.unimanager.models;

public class StatisticsResult {
  private double average;
  private double max;
  private double min;
  private double range;

  public StatisticsResult(double average, double max, double min, double range) {
    this.average = average;
    this.max = max;
    this.min = min;
    this.range = range;
  }

  public double getAverage() {
    return average;
  }

  public void setAverage(double average) {
    this.average = average;
  }

  public double getMax() {
    return max;
  }

  public void setMax(double max) {
    this.max = max;
  }

  public double getMin() {
    return min;
  }

  public void setMin(double min) {
    this.min = min;
  }

  public double getRange() {
    return range;
  }

  public void setRange(double range) {
    this.range = range;
  }
}
