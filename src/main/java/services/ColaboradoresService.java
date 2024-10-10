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
import utils.ColaboracionesHelper;
import utils.sender.Mensaje;
import utils.sender.channels.EmailSender;

/**
 * Instancia el colaborador y lo guarda en el repositorio.
 */

public class ColaboradoresService {

  private final UsuariosRepository usuariosRepository;
  private final ColaboradoresRepository colaboradoresRepository;

  public ColaboradoresService(
      UsuariosRepository usuariosRepository,
      ColaboradoresRepository colaboradoresRepository
  ) {
    this.usuariosRepository = usuariosRepository;
    this.colaboradoresRepository = colaboradoresRepository;
  }

  /**
   * Se cargan los datos a un colaborador.
   *
   * @param colaboradorInputDto Es el input del colaborador.
   */

  public void crearDesdeCsv(
      ColaboradorInputDto colaboradorInputDto, EmailSender emailSender, Colaboracion colaboracion) {

    Optional<Colaborador> posibleColaborador = this.colaboradoresRepository
        .buscarPorDocumento(Integer.parseInt(colaboradorInputDto.getNumeroDocumento()));

    Colaborador colaborador;

    if (posibleColaborador.isEmpty()) {
      colaborador = this.crear(colaboradorInputDto);

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
    } else {
      colaborador = posibleColaborador.get();
    }

    ColaboracionesHelper.realizarColaboracion(colaboracion, colaborador);
  }

  public Colaborador crear(ColaboradorInputDto colaboradorInputDto) {
    return FactoryColaborador.crearCon(colaboradorInputDto);
  }

  /**
   * Crea un con colaborador a partir de un input, una direccion y un usuario.
   *
   * @param colaboradorInputDto los datos del cobalorador recibidos del formulario.
   * @param direccion direccion del colaborador (opcional)
   * @param usuario el usuario del colaborador.
   */

  public Colaborador crear(ColaboradorInputDto colaboradorInputDto,
                           Direccion direccion,
                           Usuario usuario) {
    Colaborador colaborador = this.crear(colaboradorInputDto);

    if (colaborador.getTipoColaborador().equals(TipoColaborador.FISICO)) {
      usuario.setTipoRol(TipoRol.PERSONA_FISICA);
    } else {
      usuario.setTipoRol(TipoRol.PERSONA_JURIDICA);
    }
    colaborador.setDireccion(direccion);
    colaborador.setUsuario(usuario);

    this.colaboradoresRepository.guardar(colaborador);
    return  colaborador;
  }

}