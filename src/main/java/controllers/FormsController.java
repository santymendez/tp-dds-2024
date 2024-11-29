package controllers;

import dtos.FormularioInputdto;
import dtos.PreguntaInputdto;
import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.formulario.Pregunta;
import services.FormulariosService;
import services.PreguntasService;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Forms controller.
 */

public class FormsController implements InterfaceCrudViewsHandler {

  private final FormulariosService formulariosService;
  private final PreguntasService preguntasService;

  public FormsController(FormulariosService formulariosService,
                         PreguntasService preguntasService) {
    this.formulariosService = formulariosService;
    this.preguntasService = preguntasService;
  }

  @Override
  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Formularios");
    model.put("activeSession", true);
    model.put("tipoRol", context.sessionAttribute("tipoRol"));

    context.render("formularios.hbs", model);
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) {
    FormularioInputdto form = FormularioInputdto.from(context);
    List<Pregunta> preguntas = new ArrayList<>();

    for (int i = 0; i < Integer.parseInt(form.getCantPreguntas()); i++) {
      PreguntaInputdto preguntaInputdto = PreguntaInputdto.from(context, i);
      preguntas.add(this.preguntasService.crearCon(preguntaInputdto));
    }

    this.formulariosService.crearCon(form, preguntas);

    context.redirect("/heladeras-solidarias?formOK=true");
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
