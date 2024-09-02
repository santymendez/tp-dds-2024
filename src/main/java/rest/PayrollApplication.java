package rest;

import models.db.PersistenceUnitSwitcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * API REST launcher.
 */

@SpringBootApplication
public class PayrollApplication {
  public static void main(String[] args) {
    SpringApplication.run(PayrollApplication.class, args);
  }
}
