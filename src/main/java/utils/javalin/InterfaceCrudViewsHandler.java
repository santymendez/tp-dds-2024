package utils.javalin;

import io.javalin.http.Context;

/**
 * Interfaz para manejar operaciones CRUD para vistas.
 */

public interface InterfaceCrudViewsHandler {
  void index(Context context);

  void show(Context context);

  void create(Context context);

  void save(Context context);

  void edit(Context context);

  void update(Context context);

  void delete(Context context);
}
