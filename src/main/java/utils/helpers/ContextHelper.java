package utils.helpers;

import config.RepositoryLocator;
import io.javalin.http.Context;
import java.util.Objects;
import java.util.Optional;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.tecnico.Tecnico;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.TecnicosRepository;

/**
 * Clase Auxiliar para chequear si el parametro de un formulario es vacio.
 */

public class ContextHelper {

  public static Boolean isEmpty(Context context, String key) {
    return Objects.equals(context.formParam(key), "") || context.formParam(key) == null;
  }

  /**
   * Chequea si los valores de un formulario son vacios.
   *
   * @param context el contexto de la aplicación.
   * @param keys los valores a chequear.
   * @return true si alguno de los valores es vacio, false en caso contrario.
   */

  public static Boolean areEmpty(Context context, String... keys) {
    boolean areEmptyStatus = true;

    for (String value : keys) {
      if (!isEmpty(context, value)) {
        areEmptyStatus = false;
      }
    }

    return areEmptyStatus;
  }

  /**
   * Obtiene el colaborador de la sesión.
   *
   * @param context el contexto de la aplicación.
   * @return un Optional con el colaborador si existe, un Optional vacío en caso contrario.
   */

  public static Optional<Colaborador> getColaboradorFromContext(Context context) {
    Long usuarioId = context.sessionAttribute("idUsuario");
    return RepositoryLocator.instanceOf(ColaboradoresRepository.class)
        .buscarPorIdUsuario(usuarioId);
  }

  public static Optional<Tecnico> getTecnicoFromContext(Context context) {
    Long usuarioId = context.sessionAttribute("idUsuario");
    return RepositoryLocator.instanceOf(TecnicosRepository.class)
        .buscarPorIdUsuario(usuarioId);
  }
}
