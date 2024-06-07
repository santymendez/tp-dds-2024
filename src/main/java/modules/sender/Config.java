package modules.sender;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Esta clase se utiliza para cargar la configuracion para usar el email_sender.
 * Sigue sin ser seguro tener en texto plano una contraseña, pero es mejor que subirla
 * al repositorio de github.
 */

public class Config {
  private static Properties properties = new Properties();

  static {
    try (InputStream input = Config
        .class
        .getClassLoader()
        .getResourceAsStream("config.properties")) {
      properties.load(input);
    } catch (IOException ex) {
      throw new RuntimeException("Error loading configuration", ex);
    }
  }

  public static String getApiKey() {
    return properties.getProperty("api.key");
  }

  public static String getUser() {
    return properties.getProperty("user");
  }
}