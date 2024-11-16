package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Esta clase se utiliza para cargar la configuracion para usar el email_sender.
 * Sigue sin ser seguro tener en texto plano una contraseña, pero es mejor que subirla
 * al repositorio de github.
 */

public class Config {
  private static final Properties properties = new Properties();

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

  public static String getEmailApiKey() {
    return properties.getProperty("email.api.key");
  }

  public static String getEmailUser() {
    return properties.getProperty("email.user");
  }

  public static String getWhatsappSid() {
    return properties.getProperty("whatsapp.account.sid");
  }

  public static String getWhatsappNumber() {
    return properties.getProperty("whatsapp.account.num");
  }

  public static String getWhatsappAuthToken() {
    return properties.getProperty("whatsapp.account.token");
  }

  public static String getTelegramBotUser() {
    return properties.getProperty("telegram.bot.username");
  }

  public static String getTelegramBotToken() {
    return properties.getProperty("telegram.bot.token");
  }

  public static String getServerPort() {
    return properties.getProperty("server_port");
  }

  public static String getDevMode() {
    return properties.getProperty("dev_mode");
  }

  public static String getInitData() {
    return properties.getProperty("init_data");
  }

  public static String getTempTopic() {
    return properties.getProperty("temp_topic");
  }

  public static String getTempCli() {
    return properties.getProperty("temp_cli");
  }

  public static String getMovTopic() {
    return properties.getProperty("mov_topic");
  }

  public static String getMovCli() {
    return properties.getProperty("mov_cli");
  }

  public static String getSolicTopic() {
    return properties.getProperty("solic_topic");
  }

  public static String getSolicCli() {
    return properties.getProperty("solic_cli");
  }

  public static String getBrokerUrl() {
    return properties.getProperty("broker_url");
  }

  public static String getDataDogApiKey() {
    return properties.getProperty("data_dog_api_key");
  }

  public static String getEmailQueue() {
    return properties.getProperty("email_queue");
  }

  public static String getEmailQueueHost() {
    return properties.getProperty("email_queue_host");
  }
}