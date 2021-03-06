package pt.iade.unimanager.repositories;

import java.time.LocalDate;
import java.util.ArrayList;

import pt.iade.unimanager.models.Enrolment;
import pt.iade.unimanager.models.Student;

public class StudentRepository {
  private static ArrayList<Student> students = new ArrayList<Student>();

  public static void populate() {
    Student s = new Student("John", LocalDate.parse("2000-05-24"), 'M');
    s.setEmail("john@gmail.com");
    students.add(s);
    students.add(new Student("Mary", LocalDate.parse("1999-12-23"), 'F'));
    s = new Student("James", LocalDate.parse("2001-07-02"), 'M');
    s.setEmail("James@gmail.com");
    students.add(s);
  }

  public static void populateEnrolment() {
    students.get(0).enroll(new Enrolment(students.get(0), UnitRepository.getUnitById(0), 15));
    students.get(0).enroll(new Enrolment(students.get(0), UnitRepository.getUnitById(1), 18));
    students.get(0).enroll(new Enrolment(students.get(0), UnitRepository.getUnitById(2), 12));
    students.get(0).enroll(new Enrolment(students.get(0), UnitRepository.getUnitById(3), 9));
    students.get(0).enroll(new Enrolment(students.get(0), UnitRepository.getUnitById(4), 20));
    students.get(0).enroll(new Enrolment(students.get(0), UnitRepository.getUnitById(5), 17));

    students.get(1).enroll(new Enrolment(students.get(1), UnitRepository.getUnitById(0), 19));
    students.get(1).enroll(new Enrolment(students.get(1), UnitRepository.getUnitById(1), 13));
    students.get(1).enroll(new Enrolment(students.get(1), UnitRepository.getUnitById(2), 9));
    students.get(1).enroll(new Enrolment(students.get(1), UnitRepository.getUnitById(3), 16));
    students.get(1).enroll(new Enrolment(students.get(1), UnitRepository.getUnitById(4), 11));
    students.get(1).enroll(new Enrolment(students.get(1), UnitRepository.getUnitById(5), 18));

    students.get(2).enroll(new Enrolment(students.get(2), UnitRepository.getUnitById(0), 20));
    students.get(2).enroll(new Enrolment(students.get(2), UnitRepository.getUnitById(1), 12));
    students.get(2).enroll(new Enrolment(students.get(2), UnitRepository.getUnitById(2), 17));
    students.get(2).enroll(new Enrolment(students.get(2), UnitRepository.getUnitById(3), 19));
    students.get(2).enroll(new Enrolment(students.get(2), UnitRepository.getUnitById(4), 13));
    students.get(2).enroll(new Enrolment(students.get(2), UnitRepository.getUnitById(5), 14));

  }

  public static ArrayList<Student> getAllStudents() {
    return students;
  }

  public static Student getStudentByNumber(int number) {
    for (Student x : students) {
      if (x.getNumber() == number) {
        return x;
      }
    }

    return null;
  }

  public static boolean deleteStudentByNumber(int number) {
    return students.removeIf(x -> x.getNumber() == number);
  }

  public static void addStudent(Student student) {
    students.add(student);
  }

  public static Student getStudentByEmail(String email) {
    for (Student x : students) {
      if (x.getEmail().equals(email)) {
        return x;
      }
    }

    return null;
  }

  public static Student getStudentByName(String name) {
    for (Student x : students) {
      if (x.getName().toLowerCase().equals(name.toLowerCase())) {
        return x;
      }
    }

    return null;
  }
}
