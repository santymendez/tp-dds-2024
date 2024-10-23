package utils.helpers;

import java.time.LocalDate;

/**
 * Helper para manejar fechas.
 */

public class DateHelper {
  public static Boolean validate(LocalDate date) {
    return date.isAfter(LocalDate.now());
  }

  public static Boolean legalAge(LocalDate date) {
    return date.isBefore(LocalDate.now().minusYears(18));
  }
}
