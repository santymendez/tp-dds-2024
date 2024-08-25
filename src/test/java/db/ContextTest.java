package db;

import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import models.db.EntityManagerHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContextTest implements SimplePersistenceTest {

  @BeforeEach
  void configurarHelper() {
    EntityManagerHelper.configure("test-persistence-unit");
  }

  @Test
  void contextUp() {
    assertNotNull(entityManager());
  }

  @Test
  void contextUpWithTransaction() throws Exception {
    withTransaction(() -> {
    });
  }

}
