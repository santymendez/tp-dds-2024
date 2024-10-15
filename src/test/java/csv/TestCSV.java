package csv;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import config.RepositoryLocator;
import config.ServiceLocator;
import controllers.OldCsvController;
import models.repositories.imp.ColaboracionesRepository;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ReportesHeladerasRepository;
import models.repositories.imp.UsuariosRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.ColaboracionesService;
import services.ColaboradoresService;
import utils.sender.Mensaje;
import utils.sender.channels.EmailSender;

public class TestCSV {
  OldCsvController oldCsvController;
  ColaboradoresService colaboradoresService;
  ColaboradoresRepository colaboradoresRepository;
  ColaboracionesRepository colaboracionesRepository;
  ColaboracionesService colaboracionesService;

  @BeforeEach
  void inicializar() {

    EmailSender emailSender = mock(EmailSender.class);
    doNothing().when(emailSender).enviar(any(Mensaje.class), any(String.class));

    this.colaboradoresRepository = RepositoryLocator.instanceOf(ColaboradoresRepository.class);

    this.colaboracionesRepository = RepositoryLocator.instanceOf(ColaboracionesRepository.class);

    colaboradoresService = ServiceLocator.instanceOf(ColaboradoresService.class);

    colaboracionesService = ServiceLocator.instanceOf(ColaboracionesService.class);

    oldCsvController = new OldCsvController(colaboradoresService, emailSender, colaboracionesService);
  }

  @Test
  @DisplayName("Se realiza la carga, se guardan los usuarios y se les envia un mail")
  public void losUsuariosRecibenMail(){
    oldCsvController.leerArchivoCsv();
    Assertions.assertTrue(colaboradoresRepository.buscarPorDocumento(12345678).isPresent());
  }

}