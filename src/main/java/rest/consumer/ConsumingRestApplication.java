package rest.consumer;

import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * hola.
 */

@SpringBootApplication
public class ConsumingRestApplication {
  private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

  /**
   * hola.
   */

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(ConsumingRestApplication.class);
    app.setDefaultProperties(Collections
        .singletonMap("server.port", "8081"));
    app.run(args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplateBuilder().build();
  }

  /**
   * asd.
   */

  @Bean
  public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
    return args -> {
      String quote = restTemplate.getForObject("http://localhost:8080/api/atencion-medica/localidadesVulnerables", String.class);
      log.info(quote);
    };
  }
}
