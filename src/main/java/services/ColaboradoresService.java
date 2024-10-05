package services;

import dtos.ColaboradorInputDto;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.reconocimiento.formula.imp.Formula;
import models.entities.personas.users.TipoRol;
import models.entities.personas.users.Usuario;
import models.factories.FactoryColaborador;
import models.repositories.imp.UsuariosRepository;
import utils.sender.Mensaje;
import utils.sender.channels.EmailSender;

/**
 * Instancia el colaborador y lo guarda en el repositorio.
 */

public class ColaboradoresService {

  private final UsuariosRepository usuariosRepository;

  public ColaboradoresService(UsuariosRepository usuariosRepository) {
    this.usuariosRepository = usuariosRepository;
  }

  /**
   * Se cargan los datos a un colaborador.
   *
   * @param colaboradorInputDto Es el input del colaborador.
   */

  public Colaborador crearDesdeCsv(
          ColaboradorInputDto colaboradorInputDto, EmailSender emailSender) {

    Colaborador colaborador = FactoryColaborador.crearCon(colaboradorInputDto);

    Usuario usuario = new Usuario(colaborador.getNombre(),
        colaborador.getApellido(), TipoRol.PERSONA_FISICA);

    this.usuariosRepository.guardar(usuario);

    colaborador.setUsuario(usuario);

    Mensaje message = new Mensaje("Creación de Nuevo Usuario",
        "Se le ha creado un nuevo usuario en el sistema para ingresar. \n"
            + "\nSus credenciales son: \nUsuario: "
            + colaboradorInputDto.getContacto()
            + "\nContraseña: "
            + colaboradorInputDto.getApellido()
            + "\nPuede cambiarlas si así lo desea.\n\nSaludos!");

    String destinatario = colaboradorInputDto.getContacto();

    emailSender.enviar(message, destinatario);

    //Para los test, en realidad la formula ya deberia estar creada
    // y solo deberiamos hacer el set al reconocimiento.
    Formula formula = new Formula();
    colaborador.getReconocimiento().setFormulaCalculoDePuntos(formula);

    return colaborador;
  }
}