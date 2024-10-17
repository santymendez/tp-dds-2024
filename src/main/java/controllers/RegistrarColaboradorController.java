package controllers;

import dtos.ColaboradorInputDto;
import dtos.DireccionInputDto;
import dtos.UsuarioInputDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.users.Usuario;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ProvinciasRepository;
import models.repositories.imp.UsuariosRepository;
import services.ColaboradoresService;
import services.DireccionesService;
import services.TarjetaColaboradorService;
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
  private final TarjetaColaboradorService tarjetaColaboradorService;
  private final Autenticador autenticador;

  /**
   * Constructor de la clase.
   *
   * @param usuariosRepository        Repositorio de usuarios
   * @param genericRepository         Repositorio de colaboradores
   * @param provinciasRepository      Repositorio de provincias
   * @param direccionesService        Servicio de direcciones
   * @param usuariosService           Servicio de usuarios
   * @param colaboradoresService      Servicio de colaboradores
   * @param tarjetaColaboradorService Servicio de tarjetas de colaborador
   * @param autenticador              Autenticador
   */

  public RegistrarColaboradorController(
      UsuariosRepository usuariosRepository,
      GenericRepository genericRepository,
      ProvinciasRepository provinciasRepository,
      UsuariosService usuariosService,
      ColaboradoresService colaboradoresService,
      DireccionesService direccionesService,
      TarjetaColaboradorService tarjetaColaboradorService,
      Autenticador autenticador
  ) {
    this.usuariosRepository = usuariosRepository;
    this.genericRepository = genericRepository;
    this.usuariosService = usuariosService;
    this.colaboradoresService = colaboradoresService;
    this.autenticador = autenticador;
    this.provinciasRepository = provinciasRepository;
    this.tarjetaColaboradorService = tarjetaColaboradorService;
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

    UsuarioInputDto usuarioInputDto = UsuarioInputDto.fromContext(context);

    //Se valida que no existe ese nombre de usuario
    Optional<Usuario> posibleUsuario =
        usuariosRepository.buscarPorNombreDeUsuario(usuarioInputDto.getNombreUsuario());

    if (posibleUsuario.isPresent()) {
      Map<String, Object> model = new HashMap<>();
      model.put("titulo", "Registrarse");
      model.put("error2", "Nombre de Usuario ya registrado.");
      List<Provincia> provincias = this.provinciasRepository.buscarTodos();
      model.put("provincias", provincias);
      context.render("registrarse.hbs", model);
      return;
    }

    //Se valida la contraseña
    if (!this.autenticador.esValida(usuarioInputDto.getContrasenia())) {
      Map<String, Object> model = new HashMap<>();
      model.put("titulo", "Registrarse");
      model.put("error", "Contraseña invalida. \n" + this.autenticador.mostrarMensajesConFormato());
      List<Provincia> provincias = this.provinciasRepository.buscarTodos();
      model.put("provincias", provincias);
      context.render("registrarse.hbs", model);
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

    Usuario nuevoUsuario = this.usuariosService.crear(usuarioInputDto);
    Colaborador nuevoColaborador = this.colaboradoresService
        .crear(colaboradorDto, direccion, nuevoUsuario);

    //DECISION DE DISEÑO
    //Si ya se cargo una direccion, se envia la tarjeta de una
    this.tarjetaColaboradorService.crear(nuevoColaborador, direccion);

    //Se setea la session
    context.sessionAttribute("idUsuario", nuevoUsuario.getId());
    context.sessionAttribute("tipoRol", nuevoUsuario.getTipoRol().toString());

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
