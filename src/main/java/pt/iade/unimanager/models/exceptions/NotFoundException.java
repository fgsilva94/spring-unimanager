package pt.iade.unimanager.models.exceptions;

public class NotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public NotFoundException(String id, String elemType, String idName) {
    super(elemType + " with " + idName + " " + id + " not found.");
  }
}
