package controllers;

import dtos.DireccionInputDto;
import dtos.VulnerableInputDto;
import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;
import models.entities.personas.vulnerable.Vulnerable;
import models.repositories.imp.GenericRepository;
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
  private final TarjetasVulnerablesService tarjetasVulneralesService;

  /**
   * Construcctor de la clase.
   *
   * @param genericRepository el repo de vulnerables.
   * @param vulnerablesService    el sevice de vulnerables.
   * @param direccionesService    el service de direcciones.
   */

  public VulnerablesController(
      GenericRepository genericRepository,
      VulnerablesService vulnerablesService,
      DireccionesService direccionesService,
      TarjetasVulnerablesService tarjetasVulneralesService
  ) {
    this.genericRepository = genericRepository;
    this.vulnerablesService = vulnerablesService;
    this.direccionesService = direccionesService;
    this.tarjetasVulneralesService = tarjetasVulneralesService;
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

    Colaborador colaborador = ContextHelper.getColaboradorFromContext(context).get();

    Direccion direccion = null;

    if (direccionInputDto != null) {
      direccion = this.direccionesService.crear(direccionInputDto);
      this.genericRepository.guardar(direccion);
    }

    Vulnerable vulnerable =
        this.vulnerablesService.crear(vulnerableInputDto, direccion, colaborador);
    List<VulnerableInputDto> menoresInputDto = new ArrayList<>();

    int cantMenores = Integer.parseInt(Objects.requireNonNull(context.formParam("cantMenores")));

    for (int i = 1; i <= cantMenores; i++) {
      VulnerableInputDto menor = VulnerableInputDto.fromContext(context, i);
      menoresInputDto.add(menor);

      if (DateHelper.legalAge(menor.getFechaNacimiento())) {
        context.redirect("/heladeras-solidarias/registrar-vulnerable?oldEnough=true");
        return;
      }
    }

    this.vulnerablesService.crearMenores(menoresInputDto, vulnerable, colaborador);
    TarjetaVulnerable tarjeta = this.tarjetasVulneralesService
        .crear(colaborador, vulnerable, vulnerableInputDto.getTarjeta());

    if (tarjeta == null) {
      context.redirect("/heladeras-solidarias/vulnerables?wrongCard=true");
      return;
    }

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
