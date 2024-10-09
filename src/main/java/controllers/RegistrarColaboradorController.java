package controllers;

import dtos.ColaboradorInputDto;
import dtos.DireccionInputDto;
import dtos.UsuarioDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.entities.personas.users.Usuario;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ProvinciasRepository;
import models.repositories.imp.UsuariosRepository;
import services.ColaboradoresService;
import services.DireccionesService;
import services.UsuariosService;
import utils.javalin.InterfaceCrudViewsHandler;
import utils.security.Autenticador;

/**
 * Controller de la vista de registro de usuario,
 * junto con sus datos de colaborador.
 */

public class RegistrarColaboradorController implements InterfaceCrudViewsHandler {

  private final UsuariosRepository usuariosRepository;
  private final GenericRepository genericRepository;
  private final ProvinciasRepository provinciasRepository;
  private final UsuariosService usuariosService;
  private final ColaboradoresService colaboradoresService;
  private final DireccionesService direccionesService;
  private final Autenticador autenticador;

  /**
   * Constructor de la clase.
   *
   * @param usuariosRepository   Repositorio de usuarios
   * @param genericRepository    Repositorio de colaboradores
   * @param provinciasRepository Repositorio de provincias
   * @param direccionesService   Servicio de direcciones
   * @param usuariosService      Servicio de usuarios
   * @param colaboradoresService Servicio de colaboradores
   * @param autenticador         Autenticador
   */

  public RegistrarColaboradorController(
      UsuariosRepository usuariosRepository,
      GenericRepository genericRepository,
      ProvinciasRepository provinciasRepository,
      UsuariosService usuariosService,
      ColaboradoresService colaboradoresService,
      DireccionesService direccionesService,
      Autenticador autenticador
  ) {
    this.usuariosRepository = usuariosRepository;
    this.genericRepository = genericRepository;
    this.usuariosService = usuariosService;
    this.colaboradoresService = colaboradoresService;
    this.autenticador = autenticador;
    this.provinciasRepository = provinciasRepository;
    this.direccionesService = direccionesService;
  }

  @Override
  public void index(Context context) {
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Registrarse");

    List<Provincia> provincias = this.provinciasRepository.buscarTodos();
    model.put("provincias", provincias);

    context.render("/registrarse.hbs", model);
  }

  @Override
  public void save(Context context) {

    if (context.sessionAttribute("idUsuario") != null) {
      context.redirect("/heladeras-solidarias");
      return;
    }

    UsuarioDto usuarioDto = UsuarioDto.fromContext(context);

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
      return;
    }

    //Se valida la contraseña
    if (!autenticador.esValida(usuarioDto.getContrasenia())) {
      //TODO ver errores
      context.attribute(
          "error",
          "Contraseña invalida.\n Motivo:\n" + autenticador.mostrarMensajesConFormato()
      );
      context.redirect("/heladeras-solidarias/registrarse");
      return;
    }

    //Creación del colaborador y la dirección asociado al usuario
    ColaboradorInputDto colaboradorDto = ColaboradorInputDto.fromContext(context);
    DireccionInputDto direccionInputDto = DireccionInputDto.fromContext(context);

    Direccion direccion = null;

    if (direccionInputDto != null) {
      direccion = this.direccionesService.crear(direccionInputDto);
      this.genericRepository.guardar(direccion);
    }

    Usuario nuevoUsuario = this.usuariosService.crear(usuarioDto);
    this.colaboradoresService.crear(colaboradorDto, direccion, nuevoUsuario);

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
