package server;

import config.ControllerLocator;
import controllers.AgregarDireccionController;
import controllers.CanjearPuntosController;
import controllers.ColaboracionesController;
import controllers.CsvController;
import controllers.FormsController;
import controllers.HeladerasController;
import controllers.HomePageController;
import controllers.IniciarSesionController;
import controllers.MapaController;
import controllers.ModelosController;
import controllers.RecomendacionesController;
import controllers.RegistrarColaboradorController;
import controllers.ReportarFallaTecnicaController;
import controllers.ReportesController;
import controllers.RespuestaFormularioController;
import controllers.SuscribirseController;
import controllers.UsuariosController;
import controllers.VisitasTecnicasController;
import controllers.VulnerablesController;
import controllers.colaboraciones.DistribuirTarjetasController;
import controllers.colaboraciones.DistribuirViandasController;
import controllers.colaboraciones.DonarDineroController;
import controllers.colaboraciones.DonarViandasController;
import controllers.colaboraciones.HacerseCargoController;
import controllers.colaboraciones.RealizarOfertasController;
import io.javalin.Javalin;
import io.micrometer.core.instrument.step.StepMeterRegistry;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import models.entities.personas.users.TipoRol;
import utils.helpers.ErrorHelper;
import utils.metrics.TransactionStatus;

/**
 * Clase Router.
 */

@Slf4j
public class Router {

  /**
   * Inicializa las rutas de la aplicación.
   *
   * @param app la instancia de Javalin
   */

  public static void init(Javalin app, StepMeterRegistry registry) {

    //HOME PAGE
    app.get("/heladeras-solidarias",
        ControllerLocator.instanceOf(HomePageController.class)::index);

    // INICIAR SESION / REGISTRARSE / CERRAR SESION
    app.get("/heladeras-solidarias/iniciar-sesion",
        ControllerLocator.instanceOf(IniciarSesionController.class)::create);

    app.post("/heladeras-solidarias/iniciar-sesion", ctx -> {
      ControllerLocator.instanceOf(IniciarSesionController.class).save(ctx);

      TransactionStatus status = Objects.requireNonNull(ctx.sessionAttribute("loginStatus"));

      if (status.equals(TransactionStatus.SUCCESS)) {
        registry.counter("heladeras_solidarias.login", "status", "success").increment();
        log.info("Inicio de Sesión correcto");
      } else if (status.equals(TransactionStatus.ERROR)) {
        registry.counter("heladeras_solidarias.login", "status", "rejected").increment();
        log.warn("Inicio de Sesión incorrecto");
      }
    });

    app.get("/heladeras-solidarias/registrarse",
        ControllerLocator.instanceOf(RegistrarColaboradorController.class)::create);

    app.post("/heladeras-solidarias/registrarse",
        ControllerLocator.instanceOf(RegistrarColaboradorController.class)::save);

    app.get("/heladeras-solidarias/cerrar-sesion",
        ControllerLocator.instanceOf(IniciarSesionController.class)::update);

    //VISTAS COLABORADOR

    //VULNERABLES

    app.get("/heladeras-solidarias/vulnerables",
        ControllerLocator.instanceOf(VulnerablesController.class)::create,
        TipoRol.PERSONA_FISICA, TipoRol.ADMINISTRADOR);

    app.post("/heladeras-solidarias/vulnerables",
        ControllerLocator.instanceOf(VulnerablesController.class)::save,
        TipoRol.PERSONA_FISICA);

    //CANJEAR PUNTOS

    app.get("/heladeras-solidarias/canjear-puntos",
        ControllerLocator.instanceOf(CanjearPuntosController.class)::index,
        TipoRol.PERSONA_FISICA, TipoRol.EMPRESA_ASOCIADA, TipoRol.ADMINISTRADOR);

    app.post("/heladeras-solidarias/canjear-puntos",
        ControllerLocator.instanceOf(CanjearPuntosController.class)::update,
        TipoRol.PERSONA_FISICA, TipoRol.PERSONA_JURIDICA);

    //COLABORAR
    app.get("/heladeras-solidarias/colaborar",
        ControllerLocator.instanceOf(ColaboracionesController.class)::index,
        TipoRol.PERSONA_FISICA,
        TipoRol.PERSONA_JURIDICA,
        TipoRol.ADMINISTRADOR,
        TipoRol.EMPRESA_ASOCIADA);

    app.post("/heladeras-solidarias/colaborar", ctx -> {
      String formType = ctx.formParam("formType");

      switch (Objects.requireNonNull(formType)) {
        case "donarDinero" ->
            ControllerLocator.instanceOf(DonarDineroController.class).save(ctx);
        case "donarViandas" ->
            ControllerLocator.instanceOf(DonarViandasController.class).save(ctx);
        case "realizarOfertas" ->
            ControllerLocator.instanceOf(RealizarOfertasController.class).save(ctx);
        case "hacerseCargo" ->
            ControllerLocator.instanceOf(HacerseCargoController.class).save(ctx);
        case "distribuirTarjetas" ->
            ControllerLocator.instanceOf(DistribuirTarjetasController.class).save(ctx);
        case "distribuirViandas" ->
            ControllerLocator.instanceOf(DistribuirViandasController.class).save(ctx);
        default -> ctx.status(404).render("/error-base.hbs", ErrorHelper.generateError(
            404,
            "Página No Encontrada",
            "La página a la que quieres acceder no está disponible")
        );
      }

      TransactionStatus status = Objects.requireNonNull(ctx.sessionAttribute("colabStatus"));

      if (status.equals(TransactionStatus.SUCCESS)) {
        registry.counter("heladeras_solidarias.colaboraciones", "status", "success").increment();
        log.info("Colaboración realizada");
      } else if (status.equals(TransactionStatus.ERROR)) {
        registry.counter("heladeras_solidarias.colaboraciones", "status", "error").increment();
        log.warn("Colaboración no realizada");
      }

    }, TipoRol.PERSONA_FISICA, TipoRol.PERSONA_JURIDICA, TipoRol.EMPRESA_ASOCIADA);

    app.get("/heladeras-solidarias/heladeras",
        ControllerLocator.instanceOf(HeladerasController.class)::index,
        TipoRol.PERSONA_FISICA, TipoRol.PERSONA_JURIDICA, TipoRol.ADMINISTRADOR);

    app.post("/heladeras-solidarias/heladeras", ctx -> {
      String formType = ctx.formParam("formType");
      switch (Objects.requireNonNull(formType)) {
        case "suscribirse" ->
            ControllerLocator.instanceOf(SuscribirseController.class).save(ctx);
        case "reportarFalla" ->
            ControllerLocator.instanceOf(ReportarFallaTecnicaController.class).save(ctx);
        case "recomendaciones" ->
            ControllerLocator.instanceOf(RecomendacionesController.class).show(ctx);
        default -> ctx.status(400).result("Tipo de formulario no valido");
      }
    }, TipoRol.PERSONA_FISICA, TipoRol.PERSONA_JURIDICA, TipoRol.ADMINISTRADOR);

    app.get("/heladeras-solidarias/ver-mapa",
        ControllerLocator.instanceOf(MapaController.class)::index,
        TipoRol.PERSONA_FISICA, TipoRol.PERSONA_JURIDICA, TipoRol.ADMINISTRADOR, TipoRol.TECNICO,
        TipoRol.EMPRESA_ASOCIADA);

    app.get("/heladeras-solidarias/recomendaciones",
        ControllerLocator.instanceOf(RecomendacionesController.class)::index,
        TipoRol.PERSONA_JURIDICA, TipoRol.PERSONA_FISICA, TipoRol.ADMINISTRADOR);

    //AGREGAR DIRECCION
    app.get("/heladeras-solidarias/agregar-direccion",
        ControllerLocator.instanceOf(AgregarDireccionController.class)::index);

    app.post("/heladeras-solidarias/agregar-direccion",
        ControllerLocator.instanceOf(AgregarDireccionController.class)::save);

    //VISTAS ADMINISTRADOR
    app.get("/heladeras-solidarias/heladeras-admin",
        ControllerLocator.instanceOf(HeladerasController.class)::index,
        TipoRol.ADMINISTRADOR);

    app.post("/heladeras-solidarias/heladeras-admin", ctx -> {
      String formType = ctx.formParam("formType");
      switch (Objects.requireNonNull(formType)) {
        case "darAltaModelo" ->
            ControllerLocator.instanceOf(ModelosController.class).save(ctx);
        case "darAltaHeladera" ->
            ControllerLocator.instanceOf(HeladerasController.class).save(ctx);
        case "darBajaHeladera" ->
            ControllerLocator.instanceOf(HeladerasController.class).delete(ctx);
        case "modificarHeladera" ->
            ControllerLocator.instanceOf(HeladerasController.class).update(ctx);
        default -> ctx.status(400).result("Tipo de formulario no valido");
      }
    }, TipoRol.ADMINISTRADOR);

    // CARGAR CSV
    app.get("/heladeras-solidarias/cargar-csv",
        ControllerLocator.instanceOf(CsvController.class)::create,
        TipoRol.ADMINISTRADOR);

    app.post("heladeras-solidarias/cargar-csv",
        ControllerLocator.instanceOf(CsvController.class)::save,
        TipoRol.ADMINISTRADOR);

    app.get("/heladeras-solidarias/usuarios",
        ControllerLocator.instanceOf(UsuariosController.class)::index,
        TipoRol.ADMINISTRADOR);

    // VER REPORTES

    app.get("/heladeras-solidarias/reportes",
        ControllerLocator.instanceOf(ReportesController.class)::index,
        TipoRol.ADMINISTRADOR, TipoRol.PERSONA_JURIDICA);
    
    app.post("/heladeras-solidarias/reportes", 
        ControllerLocator.instanceOf(ReportesController.class)::save,
        TipoRol.ADMINISTRADOR, TipoRol.PERSONA_JURIDICA);

    // TECNICOS

    app.get("/heladeras-solidarias/registrar-visita",
        ControllerLocator.instanceOf(VisitasTecnicasController.class)::index,
        TipoRol.TECNICO, TipoRol.ADMINISTRADOR);

    app.post("/heladeras-solidarias/registrar-visita",
        ControllerLocator.instanceOf(VisitasTecnicasController.class)::save,
        TipoRol.TECNICO);

    // FORMS DINAMICOS

    app.get("/heladeras-solidarias/formularios",
        ControllerLocator.instanceOf(FormsController.class)::index,
        TipoRol.ADMINISTRADOR);

    app.post("/heladeras-solidarias/formularios",
        ControllerLocator.instanceOf(FormsController.class)::save,
        TipoRol.ADMINISTRADOR);

    app.get("/heladeras-solidarias/formulario-registro",
        ControllerLocator.instanceOf(RespuestaFormularioController.class)::index,
        TipoRol.PERSONA_JURIDICA, TipoRol.PERSONA_FISICA);

    app.post("/heladeras-solidarias/formulario-registro",
        ControllerLocator.instanceOf(RespuestaFormularioController.class)::save,
        TipoRol.PERSONA_JURIDICA, TipoRol.PERSONA_FISICA);
  }
}
