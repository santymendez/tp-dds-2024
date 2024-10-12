package controllers;

import dtos.DireccionInputDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.imp.GenericRepository;
import services.DireccionesService;
import services.TarjetaColaboradorService;
import utils.ContextHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * DonarViandasController.
 */

public class AgregarDireccionController implements InterfaceCrudViewsHandler {

  private final GenericRepository genericRepository;
  private final DireccionesService direccionesService;
  private final TarjetaColaboradorService tarjetaColaboradorService;

  /**
   * Constructor del controller de AgregarDireccion.
   *
   * @param genericRepository repositorio generico.
   * @param direccionesService servicio de direcciones.
   * @param tarjetaColaboradorService servicio de tarjetas de colaborador.
   */

  public AgregarDireccionController(
      GenericRepository genericRepository,
      DireccionesService direccionesService,
      TarjetaColaboradorService tarjetaColaboradorService
  ) {
    this.genericRepository = genericRepository;
    this.direccionesService = direccionesService;
    this.tarjetaColaboradorService = tarjetaColaboradorService;
  }

  @Override
  public void index(Context context) {
    List<Provincia> provincias = genericRepository.buscarTodos(Provincia.class);
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Colaborar");
    model.put("provincias", provincias);
    model.put("activeSession", true);
    model.put("tipoRol", context.sessionAttribute("tipoRol"));

    context.render("/agregar-direccion.hbs", model);
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
  }

  @Override
  public void save(Context context) {
    Colaborador colaborador = ContextHelper.getColaboradorFromContext(context).get();

    DireccionInputDto direccionDto = DireccionInputDto.fromContext(context);
    Direccion direccion = direccionesService.crearAsignar(direccionDto, colaborador);

    tarjetaColaboradorService.crear(colaborador, direccion);

    context.redirect("/heladeras-solidarias");
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
