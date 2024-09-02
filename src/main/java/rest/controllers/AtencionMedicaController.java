package rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.InformacionBarrio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.db.PersistenceUnitSwitcher;
import models.entities.personas.tarjetas.vulnerable.UsoTarjetaVulnerable;
import models.repositories.RepositoryLocator;
import models.repositories.imp.UsosTarjetasVulnerablesRepository;
import models.repositories.interfaces.InterfaceUsosTarjetasVulnerablesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/api/atencion-medica")
public class AtencionMedicaController {
  InterfaceUsosTarjetasVulnerablesRepository usosTarjetasVulnerablesRepository =
          RepositoryLocator.get("usosTarjetasVulnerablesRepository",
                  UsosTarjetasVulnerablesRepository.class);

  /**
   * Obtiene los vulnerables por cada barrio.
   */

  @GetMapping("/localidadesVulnerables")
  public String obtenerVulnerablesPorBarrio() throws JsonProcessingException {
    Map<String, InformacionBarrio> vulnerablesPorBarrio =
            this.crearMapaVulnerablesPorBarrio();

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(vulnerablesPorBarrio);
    System.out.println(json);
    return json;
  }

  /**
   * Obtiene los vulnerables del barrio indicado.
   *
   * @param nombreBarrio barrio del que se desean obtener los datos.
   */

  @GetMapping("/localidadesVulnerables/{nombreBarrio}")
  public void obtenerVulernablesPorBarrio(@PathVariable String nombreBarrio){
    // TODO
  }

  /**
   * Llena el Map con los Vulnerables por cada Barrio.
   *
   * @return el Map de vulnerables por barrio.
   */

  public Map<String, InformacionBarrio> crearMapaVulnerablesPorBarrio() {
    Map<String, InformacionBarrio> map = new HashMap<>();

    List<UsoTarjetaVulnerable> usos = usosTarjetasVulnerablesRepository.buscarTodos();

    for (UsoTarjetaVulnerable uso : usos) {
      String barrio = uso.getHeladera().getDireccion().getBarrio().getNombreBarrio();
      String vulnerable =
          uso.getTarjetaVulnerable().getRegistroVulnerable().getVulnerable().getNombre();
      this.agregarVulnerable(map, barrio, vulnerable);
    }    
    return map;
  }

  /**
   * Metodo que agrega informacion al map.
   *
   * @param map Map barrios y su informacion asiociada.
   * @param barrio Barrio para agregar/actualizar.
   * @param vulnerable Vulnerable a agregar.
   */

  private void agregarVulnerable(Map<String, InformacionBarrio> map,
                                 String barrio, String vulnerable) {

    InformacionBarrio info = map.containsKey(barrio)
        ? map.get(barrio) : new InformacionBarrio();

    info.agregarVulnerable(vulnerable);
    map.put(barrio, info);
  }
}

