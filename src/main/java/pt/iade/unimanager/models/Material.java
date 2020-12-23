package pt.iade.unimanager.models;

import pt.iade.unimanager.models.enums.MaterialState;

public class Material {
  protected String name;
  protected MaterialState state;

  public Material(String name) {
    this.name = name;
    state = MaterialState.OK;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MaterialState getState() {
    return state;
  }

  public void setState(MaterialState state) {
    this.state = state;
  }
}
