package pt.iade.unimanager.models.exceptions;

public class AlreadyExistsException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public AlreadyExistsException(String id, String elemType, String idName) {
    super(elemType + " with " + idName + " " + id + " already exists.");
  }
}
