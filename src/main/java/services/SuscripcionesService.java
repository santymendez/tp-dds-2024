package services;

import dtos.SuscripcionInputDto;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import models.entities.heladera.Heladera;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.suscripcion.Desperfecto;
import models.entities.personas.colaborador.suscripcion.FaltanViandas;
import models.entities.personas.colaborador.suscripcion.QuedanViandas;
import models.entities.personas.colaborador.suscripcion.Suscripcion;
import models.entities.personas.colaborador.suscripcion.TipoSuscripcion;

/**
 * Service para la instanciacion de Suscripciones.
 */

@NoArgsConstructor
public class SuscripcionesService {

  /**
   * Crea una lista de suscripciones a partir de un SuscripcionInputDto.
   *
   * @param suscripcionInputDto SuscripcionInputDto.
   * @return Lista de suscripciones.
   */

  public List<Suscripcion> crear(
      SuscripcionInputDto suscripcionInputDto,
      Colaborador colaborador,
      Heladera heladera
  ) {
    List<Suscripcion> suscripciones = new ArrayList<>();

    for (String tipo : suscripcionInputDto.getTiposSuscripcion()) {
      TipoSuscripcion tipoSuscripcion = TipoSuscripcion.valueOf(tipo);

      switch (tipoSuscripcion) {
        case FALTAN_N_VIANDAS:
          FaltanViandas faltanViandas =
              new FaltanViandas(colaborador, heladera,
                  Integer.parseInt(suscripcionInputDto.getViandasFaltantes()));
          suscripciones.add(faltanViandas);
          break;
        case QUEDAN_N_VIANDAS:
          QuedanViandas quedanViandas =
              new QuedanViandas(colaborador, heladera,
                  Integer.parseInt(suscripcionInputDto.getViandasRestantes()));
          suscripciones.add(quedanViandas);
          break;
        case OCURRIO_DESPERFECTO:
          Desperfecto desperfecto = new Desperfecto(colaborador, heladera);
          suscripciones.add(desperfecto);
          break;
        default:
          break;
      }
    }

    return suscripciones;
  }
}
