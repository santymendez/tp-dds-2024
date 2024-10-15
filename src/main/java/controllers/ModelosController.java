package controllers;

import dtos.ModeloInputDto;
import io.javalin.http.Context;
import models.repositories.imp.ModelosRepository;
import services.ModelosService;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controlador de modelos para alta, baja y modificacion.
 */

public class ModelosController implements InterfaceCrudViewsHandler {

  private final ModelosRepository modelosRepository;
  private final ModelosService modelosService;

  public ModelosController(
      ModelosRepository modelosRepository,
      ModelosService modelosService
  ) {
    this.modelosRepository = modelosRepository;
    this.modelosService = modelosService;
  }

  @Override
  public void index(Context context) {

  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) {
    ModeloInputDto modeloInputDto = ModeloInputDto.fromContext(context);

    if (this.modelosRepository.buscarPorNombre(modeloInputDto.getNombre()).isPresent()) {
      context.redirect("/heladeras-solidarias/heladeras-admin?modelExists=true");
    } else {
      this.modelosService.crear(modeloInputDto);
      context.redirect("/heladeras-solidarias");
    }
  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }
}
