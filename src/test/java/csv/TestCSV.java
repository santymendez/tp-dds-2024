package csv;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import config.RepositoryLocator;
import controllers.CsvController;
import models.repositories.imp.ColaboracionesRepository;
import models.repositories.imp.ColaboradoresRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.ColaboradoresService;
import utils.sender.Mensaje;
import utils.sender.channels.EmailSender;

public class TestCSV {
  CsvController csvController;
  ColaboradoresService colaboradoresService;
  ColaboradoresRepository colaboradoresRepository;
  ColaboracionesRepository colaboracionesRepository;

  @BeforeEach
  void inicializar() {

    EmailSender emailSender = mock(EmailSender.class);
    doNothing().when(emailSender).enviar(any(Mensaje.class), any(String.class));

    this.colaboradoresRepository = RepositoryLocator.instanceOf(ColaboradoresRepository.class);

    this.colaboracionesRepository = RepositoryLocator.instanceOf(ColaboracionesRepository.class);

    colaboradoresService = new ColaboradoresService();

    csvController = new CsvController(colaboradoresService, colaboradoresRepository, colaboracionesRepository, emailSender);
  }

  @Test
  @DisplayName("Se realiza la carga, se guardan los usuarios y se les envia un mail")
  public void losUsuariosRecibenMail(){
    csvController.leerArchivoCsv();
    Assertions.assertTrue(colaboradoresRepository.buscarPorDocumento(12345678).isPresent());
  }

}