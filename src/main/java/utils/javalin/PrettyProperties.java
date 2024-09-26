package utils.javalin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Clase singleton que se encarga de leer el archivo de configuración config.properties
 */

public class PrettyProperties {
  private static PrettyProperties instance = null;
  private final Properties prop;


  /**
   * Devuelve la instancia de la clase PrettyProperties.
   *
   * @return instancia de la clase PrettyProperties.
   */

  public static PrettyProperties getInstance() {
    if (instance == null) {
      instance = new PrettyProperties();
    }
    return instance;
  }

  private PrettyProperties() {
    this.prop = new Properties();
    this.readProperties();
  }

  private void readProperties() {
    try {
      InputStream input = new FileInputStream("config.properties");
      this.prop.load(input);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public String propertyFromName(String name) {
    return this.prop.getProperty(name, null);
  }

}
