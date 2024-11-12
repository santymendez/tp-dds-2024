package services;

import dtos.ColaboradorInputDto;
import java.util.Optional;
import models.entities.colaboracion.Colaboracion;
import models.entities.direccion.Direccion;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.TipoColaborador;
import models.entities.personas.users.TipoRol;
import models.entities.personas.users.Usuario;
import models.factories.FactoryColaborador;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.UsuariosRepository;
import utils.helpers.ColaboracionesHelper;
import utils.security.PasswordHasher;
import utils.sender.Mensaje;
import utils.sender.channels.EmailSender;

/**
 * Instancia el colaborador y lo guarda en el repositorio.
 */

public class ColaboradoresService {

  private final ColaboradoresRepository colaboradoresRepository;
  private final UsuariosRepository usuariosRepository;

  /**
   * Constructor de la clase.
   *
   * @param colaboradoresRepository Repositorio de colaboradores
   * @param usuariosRepository      Repositorio de usuarios
   */

  public ColaboradoresService(
      ColaboradoresRepository colaboradoresRepository,
      UsuariosRepository usuariosRepository
  ) {
    this.colaboradoresRepository = colaboradoresRepository;
    this.usuariosRepository = usuariosRepository;
  }

  /**
   * Se cargan los datos a un colaborador.
   *
   * @param colaboradorInputDto Es el input del colaborador.
   */

  public void crearDesdeCsv(
      ColaboradorInputDto colaboradorInputDto, EmailSender emailSender, Colaboracion colaboracion) {

    Optional<Colaborador> posibleColaborador = this.colaboradoresRepository
        .buscarPorDocumento(colaboradorInputDto.getNumeroDocumento());

    Colaborador colaborador;

    if (posibleColaborador.isEmpty()) {
      colaborador = FactoryColaborador.crearCon(colaboradorInputDto);

      Usuario usuario = new Usuario(
          colaborador.getNombre(),
          PasswordHasher.hashPassword(colaborador.getApellido()),
          TipoRol.PERSONA_JURIDICA
      );
      colaborador.setUsuario(usuario);

      this.usuariosRepository.guardar(usuario);
      this.colaboradoresRepository.guardar(colaborador);

      Mensaje message = new Mensaje("Creación de Nuevo Usuario",
          "Se le ha creado un nuevo usuario en el sistema para ingresar. \n"
              + "\nSus credenciales son: \nUsuario: "
              + colaborador.getNombre()
              + "\nContraseña: "
              + colaborador.getApellido()
              + "\nPuede cambiarlas si así lo desea.\n\nSaludos!");

      String destinatario = colaborador.getContacto().getInfo();

      emailSender.enviar(message, destinatario);
    } else {
      colaborador = posibleColaborador.get();
    }

    ColaboracionesHelper.realizarColaboracion(colaboracion, colaborador);
  }

  /**
   * Crea un con colaborador a partir de un input, una direccion y un usuario.
   *
   * @param colaboradorInputDto los datos del cobalorador recibidos del formulario.
   * @param direccion           direccion del colaborador (opcional)
   * @param usuario             el usuario del colaborador.
   */

  public Colaborador crear(ColaboradorInputDto colaboradorInputDto,
                           Direccion direccion,
                           Usuario usuario) {
    Colaborador colaborador = FactoryColaborador.crearCon(colaboradorInputDto);

    if (colaborador.getTipoColaborador().equals(TipoColaborador.FISICO)) {
      usuario.setTipoRol(TipoRol.PERSONA_FISICA);
    } else {
      usuario.setTipoRol(TipoRol.PERSONA_JURIDICA);
    }
    colaborador.setDireccion(direccion);
    colaborador.setUsuario(usuario);

    this.colaboradoresRepository.guardar(colaborador);
    return colaborador;
  }

}