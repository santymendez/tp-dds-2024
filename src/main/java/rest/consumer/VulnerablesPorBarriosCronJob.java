package rest.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

/**
 * CronJob.
 */

@SpringBootApplication
public class VulnerablesPorBarriosCronJob {
  private static final Logger log = LoggerFactory.getLogger(VulnerablesPorBarriosCronJob.class);

  /**
   * CronTask para obtener los Vulnerables por cada Barrio.
   */

  public static void main(String[] args) {
    RestTemplate restTemplate = new RestTemplate();
    String jsonResponse = restTemplate.getForObject("http://localhost:8080/api/atencion-medica/localidadesVulnerables", String.class);
    //agregar logica correspondiente, la api devuelve un json
    log.info(jsonResponse);

  }
}
