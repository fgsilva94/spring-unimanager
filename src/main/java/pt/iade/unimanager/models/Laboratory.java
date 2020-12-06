package pt.iade.unimanager.models;

import java.util.ArrayList;
import java.util.List;

public class Laboratory extends Room {
  List<Material> materials;

  public Laboratory(String designation, int capacity) {
    super(designation, capacity);
    materials = new ArrayList<>();
  }

  public List<Material> getMaterials() {
    return materials;
  }
}
