package controllers;

import dtos.DireccionInputDto;
import dtos.VulnerableInputDto;
import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import models.entities.colaboracion.Colaboracion;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;
import models.entities.personas.vulnerable.Vulnerable;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.TarjetasVulnerablesRepository;
import services.ColaboracionesService;
import services.DireccionesService;
import services.TarjetasVulnerablesService;
import services.VulnerablesService;
import utils.helpers.ContextHelper;
import utils.helpers.DateHelper;
import utils.javalin.InterfaceCrudViewsHandler;

/**
 * Controller para el registro de vulnerables.
 */

public class VulnerablesController implements InterfaceCrudViewsHandler {

  private final GenericRepository genericRepository;
  private final VulnerablesService vulnerablesService;
  private final DireccionesService direccionesService;
  private final TarjetasVulnerablesService tarjetasVulnerablesService;
  private final ColaboracionesService colaboracionesService;
  private final TarjetasVulnerablesRepository tarjetasVulnerablesRepository;

  /**
   * Construcctor de la clase.
   *
   * @param genericRepository el repo de vulnerables.
   * @param vulnerablesService    el sevice de vulnerables.
   * @param direccionesService    el service de direcciones.
   * @param tarjetasVulnerablesService servicio de tarjetas.
   * @param colaboracionesService servicio de colaboraciones.
   */

  public VulnerablesController(
      GenericRepository genericRepository,
      TarjetasVulnerablesRepository tarjetasVulnerablesRepository,
      VulnerablesService vulnerablesService,
      DireccionesService direccionesService,
      TarjetasVulnerablesService tarjetasVulnerablesService,
      ColaboracionesService colaboracionesService
  ) {
    this.genericRepository = genericRepository;
    this.tarjetasVulnerablesRepository = tarjetasVulnerablesRepository;
    this.vulnerablesService = vulnerablesService;
    this.direccionesService = direccionesService;
    this.tarjetasVulnerablesService = tarjetasVulnerablesService;
    this.colaboracionesService = colaboracionesService;
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
    model.put("titulo", "Registrar Vulnerable");

    List<Provincia> provincias = this.genericRepository.buscarTodos(Provincia.class);
    model.put("provincias", provincias);

    model.put("activeSession", true);
    model.put("tipoRol", context.sessionAttribute("tipoRol"));

    context.render("/registrar-vulnerable.hbs", model);
  }


  @Override
  public void save(Context context) {
    VulnerableInputDto vulnerableInputDto = VulnerableInputDto.fromContext(context);

    if (!DateHelper.legalAge(vulnerableInputDto.getFechaNacimiento())) {
      context.redirect("/heladeras-solidarias/vulnerables?missingParent=true");
      return;
    }

    DireccionInputDto direccionInputDto = DireccionInputDto.fromContext(context);

    Optional<TarjetaVulnerable> posibleTarjeta =
        this.tarjetasVulnerablesRepository.buscarPorUuid(vulnerableInputDto.getTarjeta());

    if (posibleTarjeta.isEmpty()) {
      context.redirect("/heladeras-solidarias/vulnerables?wrongCard=true");
      return;
    }

    int cantMenores = Integer.parseInt(Objects.requireNonNull(context.formParam("cantMenores")));
    List<VulnerableInputDto> menoresInputDto = new ArrayList<>();

    for (int i = 1; i <= cantMenores; i++) {
      VulnerableInputDto menor = VulnerableInputDto.fromContext(context, i);
      menoresInputDto.add(menor);

      if (DateHelper.legalAge(menor.getFechaNacimiento())) {
        context.redirect("/heladeras-solidarias/registrar-vulnerable?oldEnough=true");
        return;
      }
    }

    Direccion direccion = null;

    if (direccionInputDto != null) {
      direccion = this.direccionesService.crear(direccionInputDto);
      this.genericRepository.guardar(direccion);
    }

    Colaborador colaborador = ContextHelper.getColaboradorFromContext(context).get();

    Vulnerable vulnerable =
        this.vulnerablesService.crear(vulnerableInputDto, direccion, colaborador);

    this.vulnerablesService.crearMenores(menoresInputDto, vulnerable, colaborador);
    this.tarjetasVulnerablesService.crear(colaborador, vulnerable, posibleTarjeta.get());

    //Si no hay problema se registran los puntos del colaborador
    //La colaboracion real ya esta registrada con todas las tarjetas solicitadas
    //Pero los puntos se suman al momento de registrar a cada vulnerable
    Colaboracion colaboracionAuxiliar = this.colaboracionesService.crear(1);
    colaborador.aumentarReconocimiento(colaboracionAuxiliar);

    context.redirect("/heladeras-solidarias?vulnerableCreated=true");
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
