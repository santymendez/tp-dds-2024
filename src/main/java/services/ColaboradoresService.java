package services;

import dtos.ColaboradorInputDto;
import lombok.Setter;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.reconocimiento.formula.imp.Formula;
import models.entities.personas.contacto.Contacto;
import models.entities.personas.contacto.TipoContacto;
import models.entities.personas.documento.Documento;
import models.entities.personas.documento.TipoDocumento;
import models.repositories.InterfaceColaboradoresRepository;
import utils.security.Usuario;
import utils.sender.Destinatario;
import utils.sender.Mensaje;
import utils.sender.channels.EmailSender;

/**
 * Instancia el colaborador y lo guarda en el repositorio.
 */

public class ColaboradoresService {

  private final InterfaceColaboradoresRepository colaboradoresRepository;
  // TODO revisar si esta bien (lo saque afuera para poder moquearlo)
  @Setter
  private EmailSender emailsender = EmailSender.getInstance();

  public ColaboradoresService(InterfaceColaboradoresRepository colaboradorRepository) {
    this.colaboradoresRepository = colaboradorRepository;
  }

  /**
   * Se cargan los datos a un colaborador.
   *
   * @param colaboradorInputDto Es el input del colaborador.
   */

  public Colaborador crear(ColaboradorInputDto colaboradorInputDto) {

    Colaborador colaborador = new Colaborador();
    colaborador.setNombre(colaboradorInputDto.getNombre());
    colaborador.setApellido(colaboradorInputDto.getApellido());

    Documento documento = new Documento(colaboradorInputDto.getNumeroDocumento(),
        TipoDocumento.valueOf(colaboradorInputDto.getTipoDocumento()));
    colaborador.setDocumento(documento);

    Contacto contacto = new Contacto(colaboradorInputDto.getEmail(), TipoContacto.MAIL);
    colaborador.setContacto(contacto);

    //Crear usuario y enviar mail
    Usuario usuario =  new Usuario(colaborador.getNombre(), colaborador.getApellido());
    colaborador.setUsuario(usuario);

    Mensaje message = new Mensaje("Creación de Nuevo Usuario",
        "Se le ha creado un nuevo usuario en el sistema para ingresar. \n"
            + "\nSus credenciales son: \nUsuario: "
            + colaboradorInputDto.getEmail()
            + "\nContraseña: "
            + colaboradorInputDto.getApellido()
            + "\nPuede cambiarlas si así lo desea.\n\nSaludos!");

    // TODO revisar si esta bien tratar polimorficamente
    //  TODO el sender ya que como consecuencia tenemos que
    // mostrar mas logica del modulo (Quizas con un factory se puede arreglar, no se si vale
    // la pena).
    Destinatario destinatario = new Destinatario();
    destinatario.agregarMedioDeContacto(TipoContacto.MAIL, colaboradorInputDto.getEmail());

    emailsender.enviar(message, destinatario);

    //Para los test, en realidad la formula ya deberia estar creada
    // y solo deberiamos hacer el set al reconocimiento.
    Formula formula = new Formula();
    colaborador.getReconocimiento().setFormulaCalculoDePuntos(formula);

    colaboradoresRepository.guardar(colaborador);

    return colaborador;
  }
}