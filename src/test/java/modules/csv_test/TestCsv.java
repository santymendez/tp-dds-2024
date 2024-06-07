package modules.csv_test;

import models.repositories.ColaboradoresRepository;
import modules.bulk.load.CsvController;
import modules.sender.channels.EmailSender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.ColaboracionesService;
import services.ColaboradoresService;

import static org.mockito.Mockito.*;

public class TestCsv {
  CsvController csvController;
  ColaboradoresService colaboradoresService;
  ColaboracionesService colaboracionesService;
  ColaboradoresRepository colaboradoresRepository;

  @BeforeEach
  void inicializar() {
    colaboradoresRepository = new ColaboradoresRepository();
    colaboracionesService = new ColaboracionesService();
    colaboradoresService = new ColaboradoresService(colaboradoresRepository);
    EmailSender emailSender = mock(EmailSender.class);
    colaboradoresService.setEmailsender(emailSender);
    csvController = new CsvController(colaboradoresRepository, colaboradoresService, colaboracionesService);
  }

  @Test
  @DisplayName("Se realiza la carga, se guardan los usuarios y se les envia un mail")
  public void losUsuariosRecibenMail() {
    try {
      csvController.leerArchivoCsv();
      Assertions.assertTrue(colaboradoresRepository.buscar(12345678).isPresent());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
