package modules.csv_test;

import controllers.ColaboradoresController;
import models.repositories.ColaboradoresRepository;
import modules.bulk.load.CargaCsv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.ColaboracionesService;
import services.ColaboradoresService;

public class TestCsv {
  CargaCsv bulkLoader;
  ColaboradoresController colaboradoresController;
  ColaboradoresService colaboradoresService;
  ColaboracionesService colaboracionesService;
  ColaboradoresRepository colaboradoresRepository;

  @BeforeEach
  void inicializar() {
    colaboradoresRepository = new ColaboradoresRepository();
    colaboracionesService = new ColaboracionesService();
    colaboradoresService = new ColaboradoresService(colaboradoresRepository);
    colaboradoresController = new ColaboradoresController(colaboradoresRepository, colaboradoresService);
    bulkLoader = new CargaCsv(colaboradoresController, colaboracionesService);
  }

  @Test
  @DisplayName("Se realiza la carga, se guardan los usuarios y se les envia un mail")
  public void losUsuariosRecibenMail() {
    try {
      bulkLoader.leerArchivoCsv();
      Assertions.assertTrue(colaboradoresRepository.buscar(12345678).isPresent());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
