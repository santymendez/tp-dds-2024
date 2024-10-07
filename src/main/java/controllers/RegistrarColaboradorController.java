package controllers;

import dtos.ColaboradorInputDto;
import dtos.DireccionInputDto;
import dtos.UsuarioDto;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.TipoColaborador;
import models.entities.personas.users.TipoRol;
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
   * @param usuariosRepository Repositorio de usuarios
   * @param genericRepository Repositorio de colaboradores
   * @param provinciasRepository Repositorio de provincias
   * @param direccionesService Servicio de direcciones
   * @param usuariosService Servicio de usuarios
   * @param colaboradoresService Servicio de colaboradores
   * @param autenticador Autenticador
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
    }

    //Inicializo algunas variables locales para despues
    TipoRol rol = null;

    //Creacion del usuario
    UsuarioDto usuarioDto = new UsuarioDto(
        context.formParam("usuario"),
        context.formParam("contrasenia")
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

    //Creación del colaborador asociado al usuario
    ColaboradorInputDto colaboradorDto = new ColaboradorInputDto();

    colaboradorDto.setTipoContacto(context.formParam("tipoContacto"));
    colaboradorDto.setContacto(context.formParam("contacto"));

    String calle = context.formParam("calle");
    String numero = context.formParam("numero");
    String provincia = context.formParam("provincia");
    String barrio = context.formParam("barrio");
    String ciudad = context.formParam("ciudad");

    DireccionInputDto direccionInputDto = null;

    if (!Objects.equals(calle, "") && !Objects.equals(numero, "") && !Objects.equals(provincia, "")
              && !Objects.equals(barrio, "") && !Objects.equals(ciudad, "")){
      direccionInputDto = DireccionInputDto.builder()
          .calle(calle)
          .numero(numero)
          .provincia(provincia)
          .barrio(barrio)
          .ciudad(ciudad)
          .build();
    }

    TipoColaborador tipoColaborador = TipoColaborador.valueOf(context.formParam("tipoColaborador"));

    if (tipoColaborador == TipoColaborador.JURIDICO) {
      rol = TipoRol.PERSONA_JURIDICA;

      colaboradorDto.setTipoColaborador(tipoColaborador.toString());

      colaboradorDto.setRubro(context.formParam("rubro"));
      colaboradorDto.setTipo(context.formParam("tipoOrganizacion"));
      colaboradorDto.setRazonSocial(context.formParam("razonSocial"));

    } else if (tipoColaborador == TipoColaborador.FISICO) {
      //Rol
      rol = TipoRol.PERSONA_FISICA;

      colaboradorDto.setTipoColaborador(tipoColaborador.toString());
      colaboradorDto.setNombre(context.formParam("nombreFisica"));
      colaboradorDto.setApellido(context.formParam("apellidoFisica"));
      colaboradorDto.setTipoDocumento(context.formParam("tipoDocFisica"));
      colaboradorDto.setNumeroDocumento(context.formParam("nroDocFisica"));
    }

    //Se crean el usuario y el colaborador
    Usuario nuevoUsuario = usuariosService.crear(usuarioDto);
    nuevoUsuario.setTipoRol(rol);

    Colaborador nuevoColaborador = colaboradoresService.crear(colaboradorDto);
    nuevoColaborador.setUsuario(nuevoUsuario);

    //Si tiene direccion la guardo
    if (direccionInputDto != null) {
      Direccion direccion = this.direccionesService.crear(direccionInputDto);
      nuevoColaborador.setDireccion(direccion);
      this.genericRepository.guardar(direccion);
    }

    //Se guardan en los repos
    usuariosRepository.guardar(nuevoUsuario);
    genericRepository.guardar(nuevoColaborador);

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
