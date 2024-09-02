package rest;

import models.db.PersistenceUnitSwitcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Database loader.
 */

@Configuration
public class LoadDatabase {
  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner init() {
    PersistenceUnitSwitcher.switchPersistenceUnit("database-persistence-unit");
    return args -> {
      log.info("Loading data...");
    };
  }
}
