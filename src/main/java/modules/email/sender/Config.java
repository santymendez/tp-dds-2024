package modules.email.sender;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class Config {
  private static Properties properties = new Properties();

  static {
    try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
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