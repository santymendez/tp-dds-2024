package rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.direccion.Barrio;
import models.entities.personas.tarjetas.vulnerable.UsoTarjetaVulnerable;
import models.entities.personas.vulnerable.Vulnerable;
import models.repositories.RepositoryLocator;
import models.repositories.imp.UsosTarjetasVulnerablesRepository;
import models.repositories.interfaces.InterfaceUsosTarjetasVulnerablesRepository;
import org.springframework.web.bind.annotation.RestController;



/**
 * Clase que representa al controller del servicio
 * API REST de atencion medica.
 * Retorne una lista de las localidades con la cantidad
 * de personas que tenemos registradas como en situación
 * de calle que solicitaron al menos una vianda en ese barrio.
 * Además, los médicos nos solicitaron saber
 * también el nombre de aquellas personas.
 */

@RestController
public class AtencionMedicaController {
  InterfaceUsosTarjetasVulnerablesRepository usosTarjetasVulnerablesRepository =
          RepositoryLocator.get("usosTarjetasVulnerablesRepository",
                  UsosTarjetasVulnerablesRepository.class);

  /**
   * Devuelve el json con la info del mapa.
   */

  public void obtenerVulnerablesPorBarrio() {
    Map<Barrio, List<Vulnerable>> vulnerablesPorBarrio =
            this.crearMapaVulnerablesPorBarrio();
    String mapAsString = vulnerablesPorBarrio.toString();
    System.out.println(mapAsString);
  }

  /**
   * Llena el Map con los Vulnerables por cada Barrio.
   *
   * @return el Map de vulnerables por barrio.
   */

  private Map<Barrio, List<Vulnerable>> crearMapaVulnerablesPorBarrio() {
    Map<Barrio, List<Vulnerable>> vulnerablesPorBarrio = new HashMap<>();

    List<UsoTarjetaVulnerable> usos = usosTarjetasVulnerablesRepository.buscarTodos();

    for (UsoTarjetaVulnerable uso : usos) {
      Barrio barrio = uso.getHeladera().getDireccion().getBarrio();
      Vulnerable vul = uso.getTarjetaVulnerable().getRegistroVulnerable().getVulnerable();
      this.agregarVulnerable(vulnerablesPorBarrio, barrio, vul);
    }

    return vulnerablesPorBarrio;
  }

  private void agregarVulnerable(Map<Barrio, List<Vulnerable>> vulnerablesPorBarrio,
                                 Barrio barrio, Vulnerable vulnerable) {

    List<Vulnerable> lst = vulnerablesPorBarrio.containsKey(barrio)
            ? vulnerablesPorBarrio.get(barrio) : new ArrayList<>();

    lst.add(vulnerable);
    vulnerablesPorBarrio.put(barrio, lst);
  }

}

