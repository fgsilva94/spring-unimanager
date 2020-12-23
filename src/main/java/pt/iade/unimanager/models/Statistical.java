package pt.iade.unimanager.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Statistical {
  @JsonIgnore
  double getAverage();

  @JsonIgnore
  double getMax();

  @JsonIgnore
  double getMin();

  @JsonIgnore
  HistogramSlot[] getHistogram(int nSlots);

  @JsonIgnore
  default double getRange() {
    return getMax() - getMin();
  }

  static double getGroupAverage(List<Statistical> group) {
    double sum = 0;

    for (Statistical stat : group) {
      sum += stat.getAverage();
    }

    return sum / group.size();
  }
}
