package services;

import dtos.FormularioInputdto;
import java.util.List;
import models.entities.formulario.Formulario;
import models.entities.formulario.Pregunta;
import models.repositories.imp.GenericRepository;

/**
 * Servicio para crear los formularios.
 */

public class FormulariosService {

  private final GenericRepository repo;

  public FormulariosService(GenericRepository repo) {
    this.repo = repo;
  }

  /**
   * Crea un formulario con las preguntas ingresadas.
   *
   * @param form      DTO con los datos del formulario.
   * @param preguntas Lista con las preguntas.
   */

  public void crearCon(FormularioInputdto form, List<Pregunta> preguntas) {

    Formulario formulario = new Formulario(preguntas, form.getNombre());

    repo.guardar(formulario);
  }
}
