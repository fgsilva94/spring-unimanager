package pt.iade.unimanager.models;

import java.time.LocalDate;

public abstract class Person {
  protected String name;
  protected LocalDate birthDate;
  protected String email;
  protected char gender;

  public Person(String name, LocalDate birthDate, char gender) {
    this.name = name;
    this.birthDate = birthDate;
    this.email = "";
    this.gender = gender;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public char getGender() {
    return gender;
  }

  public void setGender(char gender) {
    this.gender = gender;
  }

  public abstract String getReference();
}
