package pt.iade.unimanager.models;

import java.util.ArrayList;
import java.util.List;

public class Computer extends Material {
  protected List<String> specifications;

  public Computer(String name, String state) {
    super(name, state);
    specifications = new ArrayList<>();
  }

  public List<String> getSpecifications() {
    return specifications;
  }
}
