
import java.time.LocalDate;
import java.util.ArrayList;
import models.db.PersistenceUnitSwitcher;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.TipoColaboracion;
import models.entities.direccion.Barrio;
import models.entities.direccion.Direccion;
import models.entities.heladera.Heladera;
import models.entities.heladera.estados.Estado;
import models.entities.heladera.estados.TipoEstado;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.TipoColaborador;
import models.entities.personas.tarjetas.vulnerable.RegistroVulnerable;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;
import models.entities.personas.tarjetas.vulnerable.UsoTarjetaVulnerable;
import models.entities.personas.vulnerable.Vulnerable;
import models.repositories.RepositoryLocator;
import models.repositories.imp.BarriosRepository;
import models.repositories.imp.ColaboracionesRepository;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.DireccionesRepository;
import models.repositories.imp.HeladerasRepository;
import models.repositories.imp.RegistrosVulnerablesRepository;
import models.repositories.imp.TarjetasVulnerablesRepository;
import models.repositories.imp.UsosTarjetasVulnerablesRepository;
import models.repositories.interfaces.InterfaceBarriosRepository;
import models.repositories.interfaces.InterfaceDireccionesRepository;
import models.repositories.interfaces.InterfaceHeladerasRepository;
import models.repositories.interfaces.InterfaceRegistrosVulnerablesRepository;
import models.repositories.interfaces.InterfaceTarjetasVulnerablesRepository;
import models.repositories.interfaces.InterfaceUsosTarjetasVulnerablesRepository;
import models.repositories.interfaces.InterfaceVulnerablesRepository;
import rest.controllers.AtencionMedicaController;

/**
 * Main de prueba.
 */

public class MainExample {

  /**
   * Metodo main.
   */

  public static void main(String[] args) {
    MainExample instance = new MainExample();

    PersistenceUnitSwitcher.switchPersistenceUnit("database-persistence-unit");

    instance.guardarColaboracion();
    instance.guardarHeladeras();
    instance.printearVulnerables();

    PersistenceUnitSwitcher.switchPersistenceUnit("simple-persistence-unit");
  }

  private void printearVulnerables() {
    AtencionMedicaController a = new AtencionMedicaController();
    a.obtenerVulnerablesPorBarrio();
  }

  private void guardarColaboracion() {
    Colaboracion colaboracion = new Colaboracion();
    colaboracion.setTipoColaboracion(TipoColaboracion.DONAR_VIANDA);
    colaboracion.setFechaColaboracion(LocalDate.of(2024, 1, 11));

    Colaborador colaborador = new Colaborador();
    colaborador.setTipoColaborador(TipoColaborador.FISICO);
    colaboracion.setColaborador(colaborador);
    RepositoryLocator.get("colaboradoresRepository", ColaboradoresRepository.class)
        .guardar(colaborador);

    RepositoryLocator.get("colaboracionesRepository", ColaboracionesRepository.class)
        .guardar(colaboracion);
  }

  private void guardarHeladeras() {
    Estado estado1 = new Estado();
    estado1.setFechaInicial(LocalDate.of(2024, 1, 11));
    estado1.setEstado(TipoEstado.ACTIVA);
    estado1.setFechaFinal(LocalDate.of(2024, 2, 11));

    Estado estado2 = new Estado();
    estado2.setFechaInicial(LocalDate.of(2024, 3, 11));
    estado2.setEstado(TipoEstado.INACTIVA_FRAUDE);
    estado2.setFechaFinal(LocalDate.of(2024, 4, 11));

    Estado estado3 = new Estado();
    estado3.setFechaInicial(LocalDate.of(2024, 5, 11));
    estado3.setEstado(TipoEstado.INACTIVA_TEMPERATURA);
    estado3.setFechaFinal(LocalDate.of(2024, 6, 11));

    Estado estado4 = new Estado();
    estado4.setFechaInicial(LocalDate.of(2024, 7, 11));
    estado4.setEstado(TipoEstado.INACTIVA_FUNCIONAL);
    estado4.setFechaFinal(LocalDate.of(2024, 8, 11));

    Estado estado5 = new Estado();
    estado5.setFechaInicial(LocalDate.of(2024, 9, 11));
    estado5.setEstado(TipoEstado.INACTIVA_FALLA_CONEXION);
    estado5.setFechaFinal(LocalDate.of(2024, 10, 11));

    Heladera heladera1 = new Heladera();
    heladera1.setNombre("inaki");
    heladera1.setEstadoActual(estado1);
    heladera1.setFechaDeCreacion(LocalDate.now());

    Barrio barrio1 = new Barrio();
    barrio1.setNombreBarrio("Caballito");

    Direccion direccion1 = new Direccion();
    direccion1.setBarrio(barrio1);

    heladera1.setDireccion(direccion1);
      
    Heladera heladera2 = new Heladera();
    heladera2.setNombre("liam");
    heladera2.setEstadoActual(estado2);
    heladera2.setFechaDeCreacion(LocalDate.now());

    Direccion direccion2 = new Direccion();
    direccion2.setBarrio(barrio1);
    heladera2.setDireccion(direccion2);
    
    Heladera heladera3 = new Heladera();
    heladera3.setNombre("santi");
    heladera3.setEstadoActual(estado3);
    heladera3.setFechaDeCreacion(LocalDate.now());

    Direccion direccion3 = new Direccion();
    direccion3.setBarrio(barrio1);
    heladera3.setDireccion(direccion3);

    InterfaceBarriosRepository barriosRepository =
        RepositoryLocator.get("barriosRepository", BarriosRepository.class);

    barriosRepository.guardar(barrio1);

    InterfaceDireccionesRepository direccionesRepository =
        RepositoryLocator.get("direccionesRepository", DireccionesRepository.class);

    direccionesRepository.guardar(direccion1);
    direccionesRepository.guardar(direccion2);
    direccionesRepository.guardar(direccion3);

    InterfaceHeladerasRepository heladerasRepository =
        RepositoryLocator.get("heladerasRepository", HeladerasRepository.class);

    heladerasRepository.guardar(heladera1);
    heladerasRepository.guardar(heladera2);
    heladerasRepository.guardar(heladera3);

    Barrio barrio2 = new Barrio();
    barrio2.setNombreBarrio("Almagro");

    Heladera heladera4 = new Heladera();
    heladera4.setNombre("mati");
    heladera4.setEstadoActual(estado4);
    heladera4.setFechaDeCreacion(LocalDate.now());

    Direccion direccion4 = new Direccion();
    direccion4.setBarrio(barrio2);
    heladera4.setDireccion(direccion4);

    Heladera heladera5 = new Heladera();
    heladera5.setNombre("augusto");
    heladera5.setEstadoActual(estado5);
    heladera5.setFechaDeCreacion(LocalDate.now());

    Direccion direccion5 = new Direccion();
    direccion5.setBarrio(barrio2);
    heladera5.setDireccion(direccion5);

    barriosRepository.guardar(barrio2);

    direccionesRepository.guardar(direccion4);
    direccionesRepository.guardar(direccion5);

    heladerasRepository.guardar(heladera4);
    heladerasRepository.guardar(heladera5);

    InterfaceVulnerablesRepository vulnerablesRepository =
        RepositoryLocator.get("vulnerablesRepository", InterfaceVulnerablesRepository.class);

    Vulnerable vul1 = new Vulnerable();
    vul1.setMenoresAcargo(new ArrayList<>());
    vulnerablesRepository.guardar(vul1);

    RegistroVulnerable regVuln1 = new RegistroVulnerable();
    regVuln1.setVulnerable(vul1);

    TarjetaVulnerable tarjeta1 = new TarjetaVulnerable(regVuln1);
    tarjeta1.setRegistroVulnerable(regVuln1);

    UsoTarjetaVulnerable uso1 = new UsoTarjetaVulnerable();
    uso1.setHeladera(heladera1);
    uso1.setTarjetaVulnerable(tarjeta1);

    InterfaceRegistrosVulnerablesRepository registrosRepository =
        RepositoryLocator.get("registrosVulnerablesRepository",
            RegistrosVulnerablesRepository.class);

    registrosRepository.guardar(regVuln1);

    InterfaceTarjetasVulnerablesRepository tarjetasRepository =
        RepositoryLocator.get("tarjetasVulnerablesRepository",
            TarjetasVulnerablesRepository.class);

    tarjetasRepository.guardar(tarjeta1);

    InterfaceUsosTarjetasVulnerablesRepository usosRepository =
        RepositoryLocator.get("usosTarjetasVulnerablesRepository",
            UsosTarjetasVulnerablesRepository.class);

    usosRepository.guardar(uso1);

    Vulnerable vul2 = new Vulnerable();
    vul2.setMenoresAcargo(new ArrayList<>());
    vulnerablesRepository.guardar(vul2);
    
    RegistroVulnerable regVuln2 = new RegistroVulnerable();
    regVuln2.setVulnerable(vul2);

    TarjetaVulnerable tarjeta2 = new TarjetaVulnerable(regVuln2);
    tarjeta2.setRegistroVulnerable(regVuln2);

    UsoTarjetaVulnerable uso2 = new UsoTarjetaVulnerable();
    uso2.setHeladera(heladera2);
    uso2.setTarjetaVulnerable(tarjeta2);

    registrosRepository.guardar(regVuln2);
    tarjetasRepository.guardar(tarjeta2);
    usosRepository.guardar(uso2);

    Vulnerable vul3 = new Vulnerable();
    vul3.setMenoresAcargo(new ArrayList<>());
    vulnerablesRepository.guardar(vul3);

    RegistroVulnerable regVuln3 = new RegistroVulnerable();
    regVuln3.setVulnerable(vul3);

    TarjetaVulnerable tarjeta3 = new TarjetaVulnerable(regVuln3);
    tarjeta3.setRegistroVulnerable(regVuln3);

    UsoTarjetaVulnerable uso3 = new UsoTarjetaVulnerable();
    uso3.setHeladera(heladera3);
    uso3.setTarjetaVulnerable(tarjeta3);

    registrosRepository.guardar(regVuln3);
    tarjetasRepository.guardar(tarjeta3);
    usosRepository.guardar(uso3);
  }
}
