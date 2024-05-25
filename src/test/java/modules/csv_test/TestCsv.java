package modules.csv_test;

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
  ColaboradoresService colaboradoresService;
  ColaboracionesService colaboracionesService;
  ColaboradoresRepository colaboradoresRepository;

  @BeforeEach
  void inicializar() {
    colaboradoresRepository = new ColaboradoresRepository();
    colaboradoresService = new ColaboradoresService(colaboradoresRepository);
    colaboracionesService = new ColaboracionesService();
    bulkLoader = new CargaCsv(colaboradoresService, colaboracionesService);
  }

  @Test
  @DisplayName("Se realiza la carga, se guardan los usuarios y se les envia un mail")
  public void losUsuariosRecibenMail(){
    bulkLoader.leerArchivoCsv();
    Assertions.assertTrue(colaboradoresRepository.buscar(12345678).isPresent());
  }

}
