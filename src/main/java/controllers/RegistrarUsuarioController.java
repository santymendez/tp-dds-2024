package controllers;

import dtos.ColaboradorInputDto;
import dtos.UsuarioDto;
import io.javalin.http.Context;
import java.util.Map;
import java.util.Optional;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.TipoColaborador;
import models.entities.personas.users.TipoRol;
import models.entities.personas.users.Usuario;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.UsuariosRepository;
import services.ColaboradoresService;
import services.UsuariosService;
import utils.javalin.InterfaceCrudViewsHandler;
import utils.security.Autenticador;

/**
 * Controller de la vista de registro de usuario,
 * junto con sus datos de colaborador.
 */

public class RegistrarUsuarioController implements InterfaceCrudViewsHandler {

  private final UsuariosRepository usuariosRepository;
  private final ColaboradoresRepository colaboradoresRepository;
  private final UsuariosService usuariosService;
  private final ColaboradoresService colaboradoresService;
  private final Autenticador autenticador;

  /**
   * Constructor de la clase.
   *
   * @param usuariosRepository Repositorio de usuarios
   * @param colaboradoresRepository Repositorio de colaboradores
   * @param usuariosService Servicio de usuarios
   * @param colaboradoresService Servicio de colaboradores
   * @param autenticador Autenticador
   */

  public RegistrarUsuarioController(
      UsuariosRepository usuariosRepository,
      ColaboradoresRepository colaboradoresRepository,
      UsuariosService usuariosService,
      ColaboradoresService colaboradoresService,
      Autenticador autenticador
  ) {
    this.usuariosRepository = usuariosRepository;
    this.colaboradoresRepository = colaboradoresRepository;
    this.usuariosService = usuariosService;
    this.colaboradoresService = colaboradoresService;
    this.autenticador = autenticador;
  }

  @Override
  public void index(Context context) {
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    context.render("/registrarse.hbs", Map.of("titulo", "Registrarse"));
  }

  @Override
  public void save(Context context) {
    //TODO arreglar esta ruta en el router
    if (context.sessionAttribute("idUsuario") != null) {
      context.redirect("/heladeras-solidarias");
    }

    TipoColaborador tipoColaborador = TipoColaborador.valueOf(context.formParam("tipoColaborador"));

    //Inicializo creo algunas variables locales para despues
    //TODO los strings se pueden sacar con un no args constructor
    String nombreUsuario = null;
    String contrasenia = null;
    TipoRol rol = null;

    //Creación del colaborador asociado al usuario
    ColaboradorInputDto colaboradorDto = new ColaboradorInputDto();

    if (tipoColaborador == TipoColaborador.JURIDICO) {
      //Rol
      rol = TipoRol.PERSONA_JURIDICA;
      
      //Usuario dto
      nombreUsuario = context.formParam("usuarioJuridico");
      contrasenia = context.formParam("contraseniaJuridico");

      //Colaborador dto
      //TODO como seria este primer string?
      colaboradorDto.setTipoColaborador(tipoColaborador.toString());
      
    } else if (tipoColaborador == TipoColaborador.FISICO) {
      //Rol
      rol = TipoRol.PERSONA_FISICA;
      
      //Usuario dto
      nombreUsuario = context.formParam("usuarioFisico");
      contrasenia = context.formParam("contraseniaFisico");
      
      //Colaborador dto
      //TODO como seria este primer string?
      colaboradorDto.setTipoColaborador(tipoColaborador.toString());
      colaboradorDto.setNombre(context.formParam("nombreFisica"));
      colaboradorDto.setApellido(context.formParam("apellidoFisica"));
      colaboradorDto.setTipoDocumento(context.formParam("tipoDocFisica"));
      colaboradorDto.setNumeroDocumento(context.formParam("nroDocFisica"));
      colaboradorDto.setContacto(context.formParam("contactoFisica"));
      colaboradorDto.setTipoContacto(context.formParam("tipoContactoFisica"));

      //Direccion
      colaboradorDto.setCalle(context.formParam("calleFisico"));
    }

    UsuarioDto usuarioDto = new UsuarioDto(
        nombreUsuario,
        contrasenia
    );

    //Se valida que no existe ese nombre de usuario
    Optional<Usuario> posibleUsuario =
        usuariosRepository.buscarPorNombreDeUsuario(usuarioDto.getNombreUsuario());

    if (posibleUsuario.isPresent()) {
      //TODO ver como imprimir los errores
      context.attribute(
          "error",
          "Nombre de usuario ya registrado."
      );
      context.redirect("/heladeras-solidarias/registrarse");
    }

    //Se valida la contraseña
    if (!autenticador.esValida(usuarioDto.getContrasenia())) {
      //TODO ver errores
      context.attribute(
          "error",
          "Contraseña invalida.\n Motivo:\n" + autenticador.mostrarMensajesConFormato()
      );
      context.redirect("/heladeras-solidarias/registrarse");
    }

    //Se crean el usuario y el colaborador
    Usuario nuevoUsuario = usuariosService.crear(usuarioDto);
    Colaborador nuevoColaborador = colaboradoresService.crear(colaboradorDto);
    nuevoColaborador.setUsuario(nuevoUsuario);

    //Se guardan en los repos
    usuariosRepository.guardar(nuevoUsuario);
    colaboradoresRepository.guardar(nuevoColaborador);

    //Se setea la session
    context.sessionAttribute("idUsuario", nuevoUsuario.getId());
    context.sessionAttribute("tipo_rol", nuevoUsuario.getTipoRol().toString());

    //Se redirige a la homepage
    context.redirect("/heladeras-solidarias");
  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }
}
