package pt.iade.unimanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pt.iade.unimanager.repositories.StudentRepository;
import pt.iade.unimanager.repositories.UnitRepository;

@SpringBootApplication
public class UnimanagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(UnimanagerApplication.class, args);
    StudentRepository.populate();
    UnitRepository.populate();
  }

}
