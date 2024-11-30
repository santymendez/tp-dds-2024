package controllers;

import dtos.FormularioOutputDto;
import dtos.RespuestaFormularioInputDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import models.entities.formulario.Formulario;
import models.entities.formulario.Respuesta;
import models.entities.formulario.RespuestaFormulario;
import models.entities.personas.colaborador.Colaborador;
import models.repositories.FormulariosRepository;
import models.repositories.imp.GenericRepository;
import services.RespuestasFormulariosService;
import services.RespuestasService;
import utils.helpers.ContextHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller de Formularios dinamicos de respuesta.
 */

public class RespuestaFormularioController implements InterfaceCrudViewsHandler {

  private final RespuestasFormulariosService respuestasFormulariosService;
  private final RespuestasService respuestasService;
  private final FormulariosRepository formulariosRepository;
  private final GenericRepository genericRepository;

  /**
   * Constructor del controlador.
   *
   * @param respuestasService Servicio de respuestas.
   * @param respuestasFormulariosService Servicio de respuestas de formularios.
   * @param formulariosRepository Repositorio de formularios.
   */

  public RespuestaFormularioController(
      RespuestasFormulariosService respuestasFormulariosService,
      RespuestasService respuestasService,
      FormulariosRepository formulariosRepository,
      GenericRepository genericRepository
  ) {
    this.respuestasFormulariosService = respuestasFormulariosService;
    this.respuestasService = respuestasService;
    this.formulariosRepository = formulariosRepository;
    this.genericRepository = genericRepository;
  }

  @Override
  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Formularios");
    model.put("activeSession", true);
    model.put("tipoRol", context.sessionAttribute("tipoRol"));

    Optional<Formulario> form = this.formulariosRepository.buscarUltimo();

    if (form.isEmpty()) {
      context.redirect("/heladeras-solidarias");
    } else {
      FormularioOutputDto formularioOutputDto = FormularioOutputDto.fromFormulario(form.get());

      model.put("formulario", formularioOutputDto);
      model.put("nombre-formulario", formularioOutputDto.getFormulario());
      context.render("mostrar-formularios.hbs", model);
    }
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

    RespuestaFormularioInputDto respuestaFormularioInputDto =
        RespuestaFormularioInputDto.fromContext(context);

    RespuestaFormulario respuestaFormulario =
        this.respuestasFormulariosService.crearCon(respuestaFormularioInputDto);
    respuestaFormulario.setColaborador(colaborador);

    List<Respuesta> respuestas =
        this.respuestasService.crearCon(respuestaFormularioInputDto.getRespuestaInputDto());
    respuestas.forEach(respuestaFormulario::agregarRespuesta);

    colaborador.setRespondioForm(true);
    this.genericRepository.modificar(colaborador);
    this.genericRepository.guardar(respuestaFormulario);

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
