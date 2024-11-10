package utils.javalin;

import config.RepositoryLocator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import models.db.PersistenceUnitSwitcher;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.DistribucionTarjetas;
import models.entities.colaboracion.DistribucionViandas;
import models.entities.colaboracion.DonacionDinero;
import models.entities.colaboracion.HacerseCargoHeladera;
import models.entities.colaboracion.RealizacionOferta;
import models.entities.colaboracion.TipoColaboracion;
import models.entities.direccion.Barrio;
import models.entities.direccion.Ciudad;
import models.entities.direccion.Direccion;
import models.entities.direccion.Provincia;
import models.entities.formulario.Formulario;
import models.entities.formulario.Opcion;
import models.entities.formulario.Pregunta;
import models.entities.formulario.Respuesta;
import models.entities.formulario.RespuestaFormulario;
import models.entities.formulario.TipoPregunta;
import models.entities.heladera.Heladera;
import models.entities.heladera.Modelo;
import models.entities.heladera.estados.Estado;
import models.entities.heladera.estados.TipoEstado;
import models.entities.heladera.incidente.Incidente;
import models.entities.heladera.incidente.TipoIncidente;
import models.entities.heladera.sensores.SensorMovimiento;
import models.entities.heladera.sensores.SensorTemperatura;
import models.entities.heladera.vianda.Comida;
import models.entities.heladera.vianda.Vianda;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.TipoColaborador;
import models.entities.personas.colaborador.canje.Oferta;
import models.entities.personas.colaborador.reconocimiento.Reconocimiento;
import models.entities.personas.colaborador.reconocimiento.formula.imp.Formula;
import models.entities.personas.colaborador.suscripcion.Desperfecto;
import models.entities.personas.colaborador.suscripcion.FaltanViandas;
import models.entities.personas.colaborador.suscripcion.QuedanViandas;
import models.entities.personas.contacto.Contacto;
import models.entities.personas.contacto.TipoContacto;
import models.entities.personas.documento.Documento;
import models.entities.personas.documento.TipoDocumento;
import models.entities.personas.tarjetas.colaborador.TarjetaColaborador;
import models.entities.personas.tarjetas.vulnerable.RegistroVulnerable;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;
import models.entities.personas.tecnico.Tecnico;
import models.entities.personas.users.TipoRol;
import models.entities.personas.users.Usuario;
import models.entities.personas.vulnerable.Vulnerable;
import models.entities.reporte.ReporteHeladera;
import models.entities.reporte.ReporteSemanal;
import models.entities.reporte.ViandasPorColaborador;
import models.repositories.imp.ColaboracionesRepository;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ReportesHeladerasRepository;
import models.repositories.imp.TecnicosRepository;
import models.repositories.imp.UsosTarjetasVulnerablesRepository;
import models.repositories.imp.UsuariosRepository;
import utils.security.PasswordHasher;

/**
 * Clase que inicializa la aplicación con datos de prueba.
 */

@Slf4j
public class Initializer {

  @Getter
  static ColaboradoresRepository colaboradoresRepository;
  @Getter
  static ColaboracionesRepository colaboracionesRepository;
  static UsosTarjetasVulnerablesRepository usosTarjetasVulnerablesRepository;
  @Getter
  static TecnicosRepository tecnicosRepository;
  @Getter
  static GenericRepository repoGenerico;
  static ReportesHeladerasRepository reportesRepository;
  static HacerseCargoHeladera hacerseCargoHeladera;
  static UsuariosRepository usuariosRepository;

  @Getter
  static Colaboracion colocarHeladera;

  static DistribucionTarjetas distribucionTarjetas;
  static TarjetaVulnerable tarjeta1;
  static TarjetaVulnerable tarjeta2;
  static TarjetaVulnerable tarjeta3;
  static List<TarjetaVulnerable> lstTarjetas;
  static Colaboracion distribuirTarjetas;

  static DistribucionViandas distribucionViandas;
  static Colaboracion distribuirViandas;

  static DonacionDinero donacionDinero;
  static Colaboracion donarDinero;

  static RealizacionOferta realizarOferta1;
  static RealizacionOferta realizarOferta2;
  static RealizacionOferta realizarOferta3;
  static RealizacionOferta realizarOferta4;

  static Oferta oferta1;
  static Oferta oferta2;
  static Oferta oferta3;
  static Oferta oferta4;

  static Colaboracion realizarOfertaColab1;
  static Colaboracion realizarOfertaColab2;
  static Colaboracion realizarOfertaColab3;
  static Colaboracion realizarOfertaColab4;

  @Getter
  static Direccion direccion1;
  static Direccion direccion2;
  static Direccion direccion3;
  static Direccion direccion4;

  static Barrio barrio1;
  static Barrio barrio2;
  static Barrio barrio3;
  static Barrio barrio4;

  static Ciudad ciudad1;
  static Ciudad ciudad2;
  static Ciudad ciudad3;
  static Ciudad ciudad4;

  @Getter
  static Provincia buenosAires;
  static Provincia ciudadAutonomaDeBuenosAires;
  static Provincia catamarca;
  static Provincia chaco;
  static Provincia chubut;
  static Provincia cordoba;
  static Provincia corrientes;
  static Provincia entreRios;
  static Provincia formosa;
  static Provincia jujuy;
  static Provincia laPampa;
  static Provincia laRioja;
  static Provincia mendoza;
  static Provincia misiones;
  static Provincia neuquen;
  static Provincia rioNegro;
  static Provincia salta;
  static Provincia sanJuan;
  static Provincia sanLuis;
  static Provincia santaCruz;
  static Provincia santaFe;
  static Provincia santiagoDelEstero;
  static Provincia tierraDelFuego;
  static Provincia tucuman;

  static Formulario formulario1;

  static List<Pregunta> lstPreguntas1;

  static Pregunta pregunta1;
  static Pregunta pregunta2;
  static Pregunta pregunta3;

  static List<Opcion> lstOpciones1;
  static List<Opcion> lstOpciones2;
  static List<Opcion> lstOpciones3;

  static Opcion opcion1;
  static Opcion opcion2;
  static Opcion opcion3;
  static Opcion opcion4;
  static Opcion opcion5;
  static Opcion opcion6;
  static Opcion opcion7;
  static Opcion opcion8;
  static Opcion opcion9;

  static Respuesta respuesta1;
  static Respuesta respuesta2;
  static Respuesta respuesta3;

  static RespuestaFormulario respuestaFormulario1;
  static RespuestaFormulario respuestaFormulario2;
  static RespuestaFormulario respuestaFormulario3;

  @Getter
  static Heladera heladera1;
  static Heladera heladera2;
  static Heladera heladera3;
  static Heladera heladeraRota;

  static List<Estado> lstEstados1;
  static List<Estado> lstEstados2;
  static List<Estado> lstEstados3;
  static List<Estado> lstEstados4;

  static Estado estado1H1;
  static Estado estado2H1;
  static Estado estado3H1;

  static Estado estado1H2;
  static Estado estado2H2;
  static Estado estado3H2;
  static Estado estado4H2;

  static Estado estado1H3;
  static Estado estado2H3;
  static Estado estado3H3;

  static Estado estado1HR;
  static Estado estado2HR;

  static SensorMovimiento sensorMovimiento1;
  static SensorMovimiento sensorMovimiento2;
  static SensorMovimiento sensorMovimiento3;
  static SensorMovimiento sensorMovimiento4;

  static SensorTemperatura sensorTemperatura1;
  static SensorTemperatura sensorTemperatura2;
  static SensorTemperatura sensorTemperatura3;
  static SensorTemperatura sensorTemperatura4;

  static List<Vianda> lstViandas1;
  static List<Vianda> lstViandas2;
  static List<Vianda> lstViandas3;

  static Vianda vianda1;
  static Vianda vianda2;
  static Vianda vianda3;
  static Vianda vianda4;
  static Vianda vianda5;
  static Vianda vianda6;
  static Vianda vianda7;
  static Vianda vianda8;
  static Vianda vianda9;

  static Comida comida1;
  static Comida comida2;
  static Comida comida3;
  static Comida comida4;
  static Comida comida5;
  static Comida comida6;
  static Comida comida7;
  static Comida comida8;
  static Comida comida9;

  static Modelo modelo1;
  static Modelo modelo2;
  static Modelo modelo3;
  static Modelo modelo4;

  static Documento documentoAugusto;

  static Formula formula;

  static Reconocimiento reconocimientoAugusto;
  static Reconocimiento reconocimientoIniaqui;
  static Reconocimiento reconocimientoMati;
  static Reconocimiento reconocimientoCitiGroup;
  static Reconocimiento reconocimientoOficinaDeCorsini;

  static Contacto contactoAugusto;
  static Contacto contactoIniaqui;
  static Contacto contactoMati;
  static Contacto contactoCitiGroup;
  static Contacto contactoLiam;
  static Contacto contactoSanti;
  static Contacto contactoCorsini;

  @Getter
  static Colaborador augusto;
  static Colaborador iniaki;
  static Colaborador mati;
  static Colaborador elCityGroup;
  static Colaborador oficinaDeCorsini;

  static TarjetaColaborador tarjetaAugusto;
  static TarjetaColaborador tarjetaIniaqui;

  static Desperfecto desperfecto;
  static FaltanViandas faltanViandas;
  static QuedanViandas quedanViandas;

  @Getter
  static Tecnico liam;
  static Tecnico santi;

  @Getter
  static Vulnerable eze;
  static Vulnerable facu;

  // Vulnerables a cargo de eze
  static List<Vulnerable> lstMenoresACargoEze;
  static Vulnerable perez;
  static Vulnerable tello;
  static Vulnerable enrique;

  // Vulnerables a cargo de facu
  static List<Vulnerable> lstMenoresACargoFacu;
  static Vulnerable villalva;

  static RegistroVulnerable registroVulnerable1;
  static RegistroVulnerable registroVulnerable2;
  static RegistroVulnerable registroVulnerable3;

  static ReporteHeladera reporteHeladera1;
  static ReporteHeladera reporteHeladera2;
  static ReporteHeladera reporteHeladera3;

  static ReporteSemanal reporteSemanal1;
  static ReporteSemanal reporteSemanal2;
  static ReporteSemanal reporteSemanal3;

  static ViandasPorColaborador viandasPorColaborador1;
  static ViandasPorColaborador viandasPorColaborador2;
  static ViandasPorColaborador viandasPorColaborador3;

  static Usuario augustoUsuario;
  static Usuario iniakiUsuario;
  static Usuario matiUsuario;
  static Usuario elCityGroupUsuario;
  static Usuario liamUsuario;
  static Usuario santiUsuario;
  static Usuario oficinaDeCorsiniUsuario;

  static Usuario admin;

  static Incidente alerta1;

  /**
   * Inicializa la aplicación con datos de prueba.
   */

  public static void init(String unidadPersistencia) {

    //Segun la unidad de persistencia que se le pase:
    // "simple-persistence-unit" -> Se conecta a la base de datos en memoria
    // "database-persistence-unit" -> Se conecta a la base de datos definida en esa unidad
    PersistenceUnitSwitcher.switchPersistenceUnit(unidadPersistencia);

    iniciarContactos();
    iniciarColaboradores();
    iniciarVulnerables();
    iniciarProvincias();
    iniciarCiudades();
    iniciarBarrios();
    iniciarDirecciones();
    iniciarEstados();
    iniciarModelos();
    iniciarHeladeras();
    iniciarSensores();
    iniciarIncidentes();
    iniciarViandas();
    iniciarRegistrosVulnerables();
    iniciarTarjetas();
    iniciarOfertas();
    iniciarColaboraciones();
    iniciarTecnicos();
    iniciarFormularios();
    iniciarSuscripciones();
    iniciarViandasPorColaborador();
    iniciarReportes();
    iniciarUsuarios();
    iniciarIncidentes();

    persistirEntidades();
  }

  private static void persistirEntidades() {
    iniciarRepos();

    usuariosRepository.guardar(augustoUsuario);
    usuariosRepository.guardar(iniakiUsuario);
    usuariosRepository.guardar(matiUsuario);
    usuariosRepository.guardar(elCityGroupUsuario);
    usuariosRepository.guardar(oficinaDeCorsiniUsuario);
    usuariosRepository.guardar(liamUsuario);
    usuariosRepository.guardar(santiUsuario);
    usuariosRepository.guardar(admin);

    repoGenerico.guardar(buenosAires);
    repoGenerico.guardar(ciudadAutonomaDeBuenosAires);
    repoGenerico.guardar(catamarca);
    repoGenerico.guardar(chaco);
    repoGenerico.guardar(chubut);
    repoGenerico.guardar(cordoba);
    repoGenerico.guardar(corrientes);
    repoGenerico.guardar(entreRios);
    repoGenerico.guardar(formosa);
    repoGenerico.guardar(jujuy);
    repoGenerico.guardar(laPampa);
    repoGenerico.guardar(laRioja);
    repoGenerico.guardar(mendoza);
    repoGenerico.guardar(misiones);
    repoGenerico.guardar(neuquen);
    repoGenerico.guardar(rioNegro);
    repoGenerico.guardar(salta);
    repoGenerico.guardar(sanJuan);
    repoGenerico.guardar(sanLuis);
    repoGenerico.guardar(santaCruz);
    repoGenerico.guardar(santaFe);
    repoGenerico.guardar(santiagoDelEstero);
    repoGenerico.guardar(tierraDelFuego);
    repoGenerico.guardar(tucuman);

    repoGenerico.guardar(direccion1);
    repoGenerico.guardar(direccion2);
    repoGenerico.guardar(direccion3);
    repoGenerico.guardar(direccion4);

    colaboradoresRepository.guardar(augusto);
    colaboradoresRepository.guardar(iniaki);
    colaboradoresRepository.guardar(mati);
    colaboradoresRepository.guardar(elCityGroup);
    colaboradoresRepository.guardar(oficinaDeCorsini);

    repoGenerico.guardar(tarjetaAugusto);
    repoGenerico.guardar(tarjetaIniaqui);

    repoGenerico.guardar(modelo1);
    repoGenerico.guardar(modelo2);
    repoGenerico.guardar(modelo3);
    repoGenerico.guardar(modelo4);

    repoGenerico.guardar(heladera1);
    repoGenerico.guardar(heladera2);
    repoGenerico.guardar(heladera3);
    repoGenerico.guardar(heladeraRota);

    repoGenerico.guardar(sensorMovimiento1);
    repoGenerico.guardar(sensorMovimiento2);
    repoGenerico.guardar(sensorMovimiento3);
    repoGenerico.guardar(sensorMovimiento4);

    repoGenerico.guardar(sensorTemperatura1);
    repoGenerico.guardar(sensorTemperatura2);
    repoGenerico.guardar(sensorTemperatura3);
    repoGenerico.guardar(sensorTemperatura4);

    repoGenerico.guardar(villalva);
    repoGenerico.guardar(enrique);
    repoGenerico.guardar(perez);
    repoGenerico.guardar(tello);
    repoGenerico.guardar(eze);
    repoGenerico.guardar(facu);

    repoGenerico.guardar(desperfecto);
    repoGenerico.guardar(faltanViandas);
    repoGenerico.guardar(quedanViandas);

    colaboracionesRepository.guardar(colocarHeladera);
    colaboracionesRepository.guardar(distribuirTarjetas);
    colaboracionesRepository.guardar(distribuirViandas);
    colaboracionesRepository.guardar(donarDinero);
    colaboracionesRepository.guardar(realizarOfertaColab1);
    colaboracionesRepository.guardar(realizarOfertaColab2);
    colaboracionesRepository.guardar(realizarOfertaColab3);
    colaboracionesRepository.guardar(realizarOfertaColab4);

    tecnicosRepository.guardar(liam);
    tecnicosRepository.guardar(santi);

    repoGenerico.guardar(formulario1);

    repoGenerico.guardar(viandasPorColaborador1);
    repoGenerico.guardar(viandasPorColaborador2);
    repoGenerico.guardar(viandasPorColaborador3);

    reportesRepository.guardar(reporteHeladera1);
    reportesRepository.guardar(reporteHeladera2);
    reportesRepository.guardar(reporteHeladera3);

    repoGenerico.guardar(reporteSemanal1);
    repoGenerico.guardar(reporteSemanal2);
    repoGenerico.guardar(reporteSemanal3);

    repoGenerico.guardar(alerta1);
  }

  static void iniciarContactos() {
    contactoAugusto = new Contacto("+54 9 11 1234-5678", TipoContacto.WHATSAPP);
    contactoIniaqui = new Contacto("54645213212", TipoContacto.TELEGRAM);
    contactoMati = new Contacto("contactofalso@gmail.com", TipoContacto.MAIL);
    contactoCitiGroup = new Contacto("citigroup@hdp.com", TipoContacto.MAIL);
    contactoLiam = new Contacto("liam@gmail.com", TipoContacto.MAIL);
    contactoSanti = new Contacto("santi@ghost.com", TipoContacto.MAIL);
    contactoCorsini = new Contacto("corsini@ghost.com", TipoContacto.MAIL);
  }

  static void iniciarColaboradores() {

    tarjetaAugusto = new TarjetaColaborador();
    tarjetaAugusto.setFechaAlta(LocalDate.of(2019, 7, 10));

    tarjetaIniaqui = new TarjetaColaborador();
    tarjetaIniaqui.setFechaAlta(LocalDate.of(2020, 9, 1));

    formula = new Formula();

    reconocimientoAugusto = new Reconocimiento();
    reconocimientoAugusto.setFormulaCalculoDePuntos(formula);

    reconocimientoIniaqui = new Reconocimiento();
    reconocimientoIniaqui.setFormulaCalculoDePuntos(formula);

    reconocimientoMati = new Reconocimiento();
    reconocimientoMati.setFormulaCalculoDePuntos(formula);

    reconocimientoCitiGroup = new Reconocimiento();
    reconocimientoCitiGroup.setFormulaCalculoDePuntos(formula);

    reconocimientoOficinaDeCorsini = new Reconocimiento();
    reconocimientoOficinaDeCorsini.setFormulaCalculoDePuntos(formula);

    documentoAugusto = new Documento(45345678, TipoDocumento.DNI);

    augusto = new Colaborador();
    augusto.setNombre("Augusto");
    augusto.setApellido("Mazzini");
    augusto.setDocumento(documentoAugusto);
    augusto.setContacto(contactoAugusto);
    augusto.setTipoColaborador(TipoColaborador.FISICO);
    augusto.setReconocimiento(reconocimientoAugusto);
    tarjetaAugusto.setColaborador(augusto);

    iniaki = new Colaborador();
    iniaki.setNombre("Iñaki");
    iniaki.setApellido("Lorences");
    iniaki.setContacto(contactoIniaqui);
    iniaki.setTipoColaborador(TipoColaborador.FISICO);
    iniaki.setReconocimiento(reconocimientoIniaqui);
    tarjetaIniaqui.setColaborador(iniaki);

    mati = new Colaborador();
    mati.setNombre("Matias");
    mati.setApellido("Jastrebow");
    mati.setContacto(contactoMati);
    mati.setTipoColaborador(TipoColaborador.FISICO);
    mati.setReconocimiento(reconocimientoMati);

    elCityGroup = new Colaborador();
    elCityGroup.setNombre("CityGroup");
    elCityGroup.setRazonSocial("El City Group PAPA.SA");
    elCityGroup.setRubro("Futbolistico");
    elCityGroup.setTipoColaborador(TipoColaborador.EMPRESA_ASOCIADA);
    elCityGroup.setContacto(contactoCitiGroup);
    elCityGroup.setReconocimiento(reconocimientoCitiGroup);

    oficinaDeCorsini = new Colaborador();
    oficinaDeCorsini.setRubro("Divorcios");
    oficinaDeCorsini.setRazonSocial("The Best Doc Ever");
    oficinaDeCorsini.setTipoColaborador(TipoColaborador.JURIDICO);
    oficinaDeCorsini.setContacto(contactoCorsini);
    oficinaDeCorsini.setReconocimiento(reconocimientoOficinaDeCorsini);
  }

  static void iniciarUsuarios() {
    augustoUsuario = new Usuario();
    augustoUsuario.setNombreUsuario(augusto.getNombre());
    augustoUsuario.setContrasenia(PasswordHasher.hashPassword(augusto.getApellido()));
    augustoUsuario.setTipoRol(TipoRol.PERSONA_FISICA);
    augusto.setUsuario(augustoUsuario);

    iniakiUsuario = new Usuario();
    iniakiUsuario.setNombreUsuario(iniaki.getNombre());
    iniakiUsuario.setContrasenia(PasswordHasher.hashPassword(iniaki.getApellido()));
    iniakiUsuario.setTipoRol(TipoRol.PERSONA_FISICA);
    iniaki.setUsuario(iniakiUsuario);

    matiUsuario = new Usuario();
    matiUsuario.setNombreUsuario(mati.getNombre());
    matiUsuario.setContrasenia(PasswordHasher.hashPassword(mati.getApellido()));
    matiUsuario.setTipoRol(TipoRol.PERSONA_FISICA);
    mati.setUsuario(matiUsuario);

    elCityGroupUsuario = new Usuario();
    elCityGroupUsuario.setNombreUsuario(elCityGroup.getRazonSocial());
    elCityGroupUsuario.setContrasenia(PasswordHasher.hashPassword(elCityGroup.getRubro()));
    elCityGroupUsuario.setTipoRol(TipoRol.EMPRESA_ASOCIADA);
    elCityGroup.setUsuario(elCityGroupUsuario);

    oficinaDeCorsiniUsuario = new Usuario();
    oficinaDeCorsiniUsuario.setNombreUsuario(oficinaDeCorsini.getRazonSocial());
    oficinaDeCorsiniUsuario
        .setContrasenia(PasswordHasher.hashPassword(oficinaDeCorsini.getRubro()));
    oficinaDeCorsiniUsuario.setTipoRol(TipoRol.PERSONA_JURIDICA);
    oficinaDeCorsini.setUsuario(oficinaDeCorsiniUsuario);

    santiUsuario = new Usuario();
    santiUsuario.setNombreUsuario(santi.getNombre());
    santiUsuario.setContrasenia(PasswordHasher.hashPassword(santi.getApellido()));
    santiUsuario.setTipoRol(TipoRol.TECNICO);
    santi.setUsuario(santiUsuario);

    liamUsuario = new Usuario();
    liamUsuario.setNombreUsuario(liam.getNombre());
    liamUsuario.setContrasenia(PasswordHasher.hashPassword(liam.getApellido()));
    liamUsuario.setTipoRol(TipoRol.TECNICO);
    liam.setUsuario(liamUsuario);

    admin = new Usuario();
    admin.setNombreUsuario("admin");
    admin.setContrasenia(PasswordHasher.hashPassword("admin"));
    admin.setTipoRol(TipoRol.ADMINISTRADOR);
  }

  static void iniciarVulnerables() {

    lstMenoresACargoEze = new ArrayList<>();
    lstMenoresACargoFacu = new ArrayList<>();

    perez = new Vulnerable();
    perez.setNombre("Falcon Perez");
    perez.setFechaNacimiento(LocalDate.of(1972, 4, 22));

    tello = new Vulnerable();
    tello.setNombre("Facundo Tello");
    tello.setFechaNacimiento(LocalDate.of(1982, 5, 3));

    villalva = new Vulnerable();
    villalva.setNombre("El keko Villalva");
    villalva.setFechaNacimiento(LocalDate.of(1975, 6, 4));

    enrique = new Vulnerable();
    enrique.setNombre("Enrique Pinti");
    enrique.setFechaNacimiento(LocalDate.of(1999, 7, 5));

    lstMenoresACargoEze.add(perez);
    lstMenoresACargoEze.add(tello);
    lstMenoresACargoEze.add(enrique);

    lstMenoresACargoFacu.add(villalva);

    eze = new Vulnerable();
    eze.setNombre("Ezequiel");
    eze.setFechaNacimiento(LocalDate.of(1994, 8, 31));
    eze.setMenoresAcargo(lstMenoresACargoEze);

    facu = new Vulnerable();
    facu.setNombre("Facundo");
    facu.setFechaNacimiento(LocalDate.of(2001, 7, 30));
    facu.setMenoresAcargo(lstMenoresACargoFacu);
  }

  static void iniciarColaboraciones() {

    // Colaboracion - Colocar Heladera

    hacerseCargoHeladera = new HacerseCargoHeladera();
    hacerseCargoHeladera.setHeladeraColocada(heladera2);

    colocarHeladera = new Colaboracion();
    colocarHeladera.setFechaColaboracion(LocalDate.of(2021, 10, 10));
    colocarHeladera.setHacerseCargoHeladera(hacerseCargoHeladera);
    colocarHeladera.setTipoColaboracion(TipoColaboracion.HACERSE_CARGO_HELADERA);
    colocarHeladera.setColaborador(augusto);

    // Colaboracion - Distribuir Tarjetas

    distribucionTarjetas = new DistribucionTarjetas();
    distribucionTarjetas.setCantTarjetasEntregadas(3);
    distribucionTarjetas.setTarjetasEntregadas(lstTarjetas);

    distribuirTarjetas = new Colaboracion();
    distribuirTarjetas.setColaborador(iniaki);
    distribuirTarjetas.setTipoColaboracion(TipoColaboracion.ENTREGAR_TARJETA);
    distribuirTarjetas.setDistribucionTarjetas(distribucionTarjetas);
    distribuirTarjetas.setFechaColaboracion(LocalDate.of(2023, 10, 28));

    // Colaboracion - Distribuir Viandas

    distribucionViandas = new DistribucionViandas();
    distribucionViandas.setMotivoDistribucion("Habia hambre");
    distribucionViandas.setCantViandasDistribuidas(3);
    distribucionViandas.setHeladeraOrigen(heladera1);
    distribucionViandas.setHeladeraDestino(heladera3);

    distribuirViandas = new Colaboracion();
    distribuirViandas.setColaborador(mati);
    distribuirViandas.setTipoColaboracion(TipoColaboracion.DISTRIBUIR_VIANDAS);
    distribuirViandas.setDistribucionViandas(distribucionViandas);
    distribuirViandas.setFechaColaboracion(LocalDate.of(2022, 1, 10));

    // Colaboracion - Donar Dinero

    donacionDinero = new DonacionDinero();
    donacionDinero.setMontoDonado(150000);
    donacionDinero.setFrecuenciaDonacion("Unica");

    donarDinero = new Colaboracion();
    donarDinero.setFechaColaboracion(LocalDate.of(2011, 6, 26));
    donarDinero.setTipoColaboracion(TipoColaboracion.DONAR_DINERO);
    donarDinero.setColaborador(iniaki);
    donarDinero.setDonacionDinero(donacionDinero);


    // Les asigno doy los puntos por las colaboraciones

    augusto.getReconocimiento().sumarPuntos(colocarHeladera);
    iniaki.getReconocimiento().sumarPuntos(distribuirTarjetas);
    mati.getReconocimiento().sumarPuntos(distribuirViandas);
    iniaki.getReconocimiento().sumarPuntos(donarDinero);

    // Colaboracion - Realizar Ofertas

    realizarOferta1 = new RealizacionOferta();
    realizarOferta1.setOfertaRealizada(oferta1);

    realizarOferta2 = new RealizacionOferta();
    realizarOferta2.setOfertaRealizada(oferta2);

    realizarOferta3 = new RealizacionOferta();
    realizarOferta3.setOfertaRealizada(oferta3);

    realizarOferta4 = new RealizacionOferta();
    realizarOferta4.setOfertaRealizada(oferta4);

    realizarOfertaColab1 = new Colaboracion();
    realizarOfertaColab1.setColaborador(elCityGroup);
    realizarOfertaColab1.setTipoColaboracion(TipoColaboracion.REALIZAR_OFERTAS);
    realizarOfertaColab1.setFechaColaboracion(LocalDate.of(2012, 7, 27));
    realizarOfertaColab1.setOfertaRealizada(realizarOferta1);

    realizarOfertaColab2 = new Colaboracion();
    realizarOfertaColab2.setColaborador(elCityGroup);
    realizarOfertaColab2.setTipoColaboracion(TipoColaboracion.REALIZAR_OFERTAS);
    realizarOfertaColab2.setFechaColaboracion(LocalDate.of(2012, 7, 27));
    realizarOfertaColab2.setOfertaRealizada(realizarOferta2);

    realizarOfertaColab3 = new Colaboracion();
    realizarOfertaColab3.setColaborador(elCityGroup);
    realizarOfertaColab3.setTipoColaboracion(TipoColaboracion.REALIZAR_OFERTAS);
    realizarOfertaColab3.setFechaColaboracion(LocalDate.of(2012, 7, 27));
    realizarOfertaColab3.setOfertaRealizada(realizarOferta3);

    realizarOfertaColab4 = new Colaboracion();
    realizarOfertaColab4.setColaborador(elCityGroup);
    realizarOfertaColab4.setTipoColaboracion(TipoColaboracion.REALIZAR_OFERTAS);
    realizarOfertaColab4.setFechaColaboracion(LocalDate.of(2022, 7, 11));
    realizarOfertaColab4.setOfertaRealizada(realizarOferta4);
  }

  static void iniciarTarjetas() {

    lstTarjetas = new ArrayList<>();

    tarjeta1 = new TarjetaVulnerable();

    tarjeta2 = new TarjetaVulnerable();

    tarjeta3 = new TarjetaVulnerable();

    lstTarjetas.add(tarjeta1);
    lstTarjetas.add(tarjeta2);
    lstTarjetas.add(tarjeta3);
  }

  static void iniciarRegistrosVulnerables() {

    registroVulnerable1 = new RegistroVulnerable();
    registroVulnerable1.setFechaRegistro(LocalDate.of(2009, 1, 22));
    registroVulnerable1.setColaborador(iniaki);
    registroVulnerable1.setFechaAlta(LocalDate.of(2009, 7, 15));
    registroVulnerable1.setVulnerable(facu);

    registroVulnerable2 = new RegistroVulnerable();
    registroVulnerable2.setFechaRegistro(LocalDate.of(2010, 2, 23));
    registroVulnerable2.setColaborador(iniaki);
    registroVulnerable2.setFechaAlta(LocalDate.of(2010, 8, 16));
    registroVulnerable2.setVulnerable(eze);

    registroVulnerable3 = new RegistroVulnerable();
    registroVulnerable3.setFechaRegistro(LocalDate.of(2011, 3, 24));
    registroVulnerable3.setColaborador(iniaki);
    registroVulnerable3.setFechaAlta(LocalDate.of(2011, 9, 17));
    registroVulnerable3.setVulnerable(enrique);
  }

  static void iniciarHeladeras() {

    heladera1 = new Heladera();
    heladera1.setFechaDeCreacion(LocalDate.now());
    heladera1.setEstadoActual(estado3H1);
    heladera1.setDireccion(direccion1);
    heladera1.setNombre("Heladera Porteña");
    heladera1.setEstadosHeladera(lstEstados1);
    heladera1.setModelo(modelo1);
    modelo1.setCapacidadMaximaViandas(18);

    heladera2 = new Heladera();
    heladera2.setFechaDeCreacion(LocalDate.of(2021, 10, 10));
    heladera2.setEstadoActual(estado4H2);
    heladera2.setDireccion(direccion2);
    heladera2.setNombre("Heladera Cordobesa");
    heladera2.setEstadosHeladera(lstEstados2);
    heladera2.setModelo(modelo2);
    modelo2.setCapacidadMaximaViandas(25);

    heladera3 = new Heladera();
    heladera3.setFechaDeCreacion(LocalDate.now());
    heladera3.setEstadoActual(estado3H3);
    heladera3.setDireccion(direccion3);
    heladera3.setNombre("Heladera Santiagueña");
    heladera3.setEstadosHeladera(lstEstados3);
    heladera3.setModelo(modelo3);
    modelo3.setCapacidadMaximaViandas(4);

    heladeraRota = new Heladera();
    heladeraRota.setFechaDeCreacion(LocalDate.of(2012, 9, 15));
    heladeraRota.setEstadoActual(estado2HR);
    heladeraRota.setDireccion(direccion4);
    heladeraRota.setNombre("Heladera Rota");
    heladeraRota.setEstadosHeladera(lstEstados4);
    heladeraRota.setModelo(modelo4);
    modelo4.setCapacidadMaximaViandas(0);
  }

  static void iniciarViandas() {
    lstViandas1 = new ArrayList<>();
    lstViandas2 = new ArrayList<>();
    lstViandas3 = new ArrayList<>();

    comida1 = new Comida("Hamburguesa", LocalDate.of(2024, 9, 3));
    comida2 = new Comida("Ensalada", LocalDate.of(2024, 10, 3));
    comida3 = new Comida("Milanesa", LocalDate.of(2024, 11, 3));
    comida4 = new Comida("Pizza", LocalDate.of(2024, 12, 3));
    comida5 = new Comida("Sushi", LocalDate.of(2024, 1, 3));
    comida6 = new Comida("Tacos", LocalDate.of(2024, 2, 3));
    comida7 = new Comida("Lasagna", LocalDate.of(2024, 3, 3));
    comida8 = new Comida("Empanadas", LocalDate.of(2024, 4, 3));
    comida9 = new Comida("Paella", LocalDate.of(2024, 5, 3));

    vianda1 = new Vianda(comida1,
        LocalDate.of(2024, 9, 10),
        mati,
        heladera1,
        480,
        270.5F,
        false
    );

    vianda2 = new Vianda(comida2,
        LocalDate.of(2024, 9, 18),
        mati,
        heladera1,
        350,
        220.0F,
        false
    );

    vianda3 = new Vianda(comida3,
        LocalDate.of(2024, 9, 25),
        mati,
        heladera2,
        500,
        280.3F,
        false
    );

    vianda4 = new Vianda(comida4,
        LocalDate.of(2024, 10, 2),
        mati,
        heladera2,
        520,
        290.7F,
        false
    );

    vianda5 = new Vianda(comida5,
        LocalDate.of(2024, 10, 10),
        mati,
        heladera2,
        320,
        230.5F,
        false
    );

    vianda6 = new Vianda(comida6,
        LocalDate.of(2024, 10, 18),
        mati,
        heladera2,
        450,
        255.8F,
        false
    );

    vianda7 = new Vianda(comida7,
        LocalDate.of(2024, 10, 25),
        mati,
        heladera3,
        540,
        300.0F,
        false
    );

    vianda8 = new Vianda(comida8,
        LocalDate.of(2024, 11, 3),
        mati,
        heladera3,
        420,
        245.6F,
        false
    );

    vianda9 = new Vianda(comida9,
        LocalDate.of(2024, 11, 10),
        mati,
        heladera3,
        600,
        310.2F,
        false
    );


    lstViandas1.add(vianda1);
    lstViandas1.add(vianda2);

    lstViandas2.add(vianda3);
    lstViandas2.add(vianda4);
    lstViandas2.add(vianda5);
    lstViandas2.add(vianda6);

    lstViandas3.add(vianda7);
    lstViandas3.add(vianda8);
    lstViandas3.add(vianda9);

    heladera1.setViandas(lstViandas1);
    heladera2.setViandas(lstViandas2);
    heladera3.setViandas(lstViandas3);
  }

  static void iniciarDirecciones() {

    direccion1 = new Direccion();
    direccion1.setBarrio(barrio1);
    direccion1.setNombreUbicacion("Segurola 4310, Villa Devoto, CABA");
    direccion1.setLongitud(-58.517341F);
    direccion1.setLatitud(-34.605857F);

    direccion2 = new Direccion();
    direccion2.setBarrio(barrio2);
    direccion2.setNombreUbicacion("Ruy Díaz de Guzmán 675, X5800 Río Cuarto, Córdoba");
    direccion2.setLongitud(-64.353041F);
    direccion2.setLatitud(-33.136980F);

    direccion3 = new Direccion();
    direccion3.setBarrio(barrio3);
    direccion3.setNombreUbicacion("Lavalle 800, G4300 La Banda, Santiago del Estero");
    direccion3.setLongitud(-64.239235F);
    direccion3.setLatitud(-27.732836F);

    direccion4 = new Direccion();
    direccion4.setBarrio(barrio4);
    direccion4.setNombreUbicacion("B6064 Florentino Ameghino, Provincia de Buenos Aires");
    direccion4.setLongitud(-62.4674F);
    direccion4.setLatitud(-34.8461F);
  }

  static void iniciarBarrios() {

    barrio1 = new Barrio();
    barrio1.setCiudad(ciudad1);
    barrio1.setNombreBarrio("Villa Devoto");
    barrio1.setCalle("Segurola");
    barrio1.setNumero(4310);

    barrio2 = new Barrio();
    barrio2.setCiudad(ciudad2);
    barrio2.setNombreBarrio("Villa Alem");
    barrio2.setNumero(675);
    barrio2.setCalle("Ruy Díaz de Guzmán");

    barrio3 = new Barrio();
    barrio3.setCiudad(ciudad3);
    barrio3.setNombreBarrio("Villa Griselda");
    barrio3.setNumero(800);
    barrio3.setCalle("Lavalle");

    barrio4 = new Barrio();
    barrio4.setCiudad(ciudad4);
    barrio4.setNombreBarrio("Ameghino Viejo");
    barrio4.setNumero(15);
    barrio4.setCalle("San Martin");

  }

  static void iniciarCiudades() {

    ciudad1 = new Ciudad();
    ciudad1.setProvincia(buenosAires);
    ciudad1.setNombreCiudad("CABA");

    ciudad2 = new Ciudad();
    ciudad2.setProvincia(cordoba);
    ciudad2.setNombreCiudad("Rio cuarto");

    ciudad3 = new Ciudad();
    ciudad3.setProvincia(santiagoDelEstero);
    ciudad3.setNombreCiudad("La Banda");

    ciudad4 = new Ciudad();
    ciudad4.setProvincia(buenosAires);
    ciudad4.setNombreCiudad("Florentino Ameghino");
  }

  static void iniciarProvincias() {
    buenosAires = new Provincia();
    buenosAires.setNombreProvincia("Buenos Aires");

    ciudadAutonomaDeBuenosAires = new Provincia();
    ciudadAutonomaDeBuenosAires.setNombreProvincia("Ciudad Autónoma de Buenos Aires");

    catamarca = new Provincia();
    catamarca.setNombreProvincia("Catamarca");

    chaco = new Provincia();
    chaco.setNombreProvincia("Chaco");

    chubut = new Provincia();
    chubut.setNombreProvincia("Chubut");

    cordoba = new Provincia();
    cordoba.setNombreProvincia("Córdoba");

    corrientes = new Provincia();
    corrientes.setNombreProvincia("Corrientes");

    entreRios = new Provincia();
    entreRios.setNombreProvincia("Entre Ríos");

    formosa = new Provincia();
    formosa.setNombreProvincia("Formosa");

    jujuy = new Provincia();
    jujuy.setNombreProvincia("Jujuy");

    laPampa = new Provincia();
    laPampa.setNombreProvincia("La Pampa");

    laRioja = new Provincia();
    laRioja.setNombreProvincia("La Rioja");

    mendoza = new Provincia();
    mendoza.setNombreProvincia("Mendoza");

    misiones = new Provincia();
    misiones.setNombreProvincia("Misiones");

    neuquen = new Provincia();
    neuquen.setNombreProvincia("Neuquén");

    rioNegro = new Provincia();
    rioNegro.setNombreProvincia("Río Negro");

    salta = new Provincia();
    salta.setNombreProvincia("Salta");

    sanJuan = new Provincia();
    sanJuan.setNombreProvincia("San Juan");

    sanLuis = new Provincia();
    sanLuis.setNombreProvincia("San Luis");

    santaCruz = new Provincia();
    santaCruz.setNombreProvincia("Santa Cruz");

    santaFe = new Provincia();
    santaFe.setNombreProvincia("Santa Fe");

    santiagoDelEstero = new Provincia();
    santiagoDelEstero.setNombreProvincia("Santiago del Estero");

    tierraDelFuego = new Provincia();
    tierraDelFuego.setNombreProvincia("Tierra del Fuego");

    tucuman = new Provincia();
    tucuman.setNombreProvincia("Tucumán");

  }

  static void iniciarEstados() {

    // Estados de la primer heladera

    estado1H1 = new Estado();
    estado1H1.setEstado(TipoEstado.ACTIVA);
    estado1H1.setFechaInicial(LocalDate.of(2021, 1, 1));
    estado1H1.setFechaFinal(LocalDate.of(2021, 6, 30));

    estado2H1 = new Estado();
    estado2H1.setEstado(TipoEstado.INACTIVA_FUNCIONAL);
    estado2H1.setFechaInicial(LocalDate.of(2021, 7, 1));
    estado2H1.setFechaFinal(LocalDate.of(2022, 6, 30));

    estado3H1 = new Estado();
    estado3H1.setEstado(TipoEstado.ACTIVA);
    estado3H1.setFechaInicial(LocalDate.of(2022, 7, 1));
    estado3H1.setFechaFinal(LocalDate.now());

    // Estados de la segunda heladera

    estado1H2 = new Estado();
    estado1H2.setEstado(TipoEstado.ACTIVA);
    estado1H2.setFechaInicial(LocalDate.of(2021, 1, 1));
    estado1H2.setFechaFinal(LocalDate.of(2022, 3, 31));

    estado2H2 = new Estado();
    estado2H2.setEstado(TipoEstado.INACTIVA_TEMPERATURA);
    estado2H2.setFechaInicial(LocalDate.of(2022, 4, 1));
    estado2H2.setFechaFinal(LocalDate.of(2022, 4, 15));

    estado3H2 = new Estado();
    estado3H2.setEstado(TipoEstado.INACTIVA_FUNCIONAL);
    estado3H2.setFechaInicial(LocalDate.of(2022, 4, 16));
    estado3H2.setFechaFinal(LocalDate.of(2022, 5, 15));

    estado4H2 = new Estado();
    estado4H2.setEstado(TipoEstado.ACTIVA);
    estado4H2.setFechaInicial(LocalDate.of(2022, 5, 16));
    estado4H2.setFechaFinal(LocalDate.now());

    // Estados de la tercer heladera

    estado1H3 = new Estado();
    estado1H3.setEstado(TipoEstado.ACTIVA);
    estado1H3.setFechaInicial(LocalDate.of(2021, 1, 1));
    estado1H3.setFechaFinal(LocalDate.of(2021, 3, 31));

    estado2H3 = new Estado();
    estado2H3.setEstado(TipoEstado.INACTIVA_TEMPERATURA);
    estado2H3.setFechaInicial(LocalDate.of(2021, 4, 1));
    estado2H3.setFechaFinal(LocalDate.of(2021, 6, 30));

    estado3H3 = new Estado();
    estado3H3.setEstado(TipoEstado.ACTIVA);
    estado3H3.setFechaInicial(LocalDate.of(2021, 7, 1));
    estado3H3.setFechaFinal(LocalDate.now());

    // Estados de la heladera rota

    estado1HR = new Estado();
    estado1HR.setEstado(TipoEstado.ACTIVA);
    estado1HR.setFechaInicial(LocalDate.of(2021, 1, 1));
    estado1HR.setFechaFinal(LocalDate.of(2023, 4, 15));

    estado2HR = new Estado();
    estado2HR.setEstado(TipoEstado.INACTIVA_FRAUDE);
    estado2HR.setFechaInicial(LocalDate.of(2023, 4, 16));
    estado2HR.setFechaFinal(LocalDate.now());

    lstEstados1 = new ArrayList<>();
    lstEstados2 = new ArrayList<>();
    lstEstados3 = new ArrayList<>();
    lstEstados4 = new ArrayList<>();

    lstEstados1.add(estado1H1);
    lstEstados1.add(estado2H1);
    lstEstados1.add(estado3H1);

    lstEstados2.add(estado1H2);
    lstEstados2.add(estado2H2);
    lstEstados2.add(estado3H2);
    lstEstados2.add(estado4H2);

    lstEstados3.add(estado1H3);
    lstEstados3.add(estado2H3);
    lstEstados3.add(estado3H3);

    lstEstados4.add(estado1HR);
    lstEstados4.add(estado2HR);
  }

  static void iniciarModelos() {
    modelo1 = new Modelo();
    modelo1.setNombre("MK-2606");
    modelo1.setTemperaturaMaxima(18F);
    modelo1.setTemperaturaMinima(-15F);

    modelo2 = new Modelo();
    modelo2.setNombre("MK-2647");
    modelo2.setTemperaturaMaxima(20F);
    modelo2.setTemperaturaMinima(-10F);

    modelo3 = new Modelo();
    modelo3.setNombre("MK-2688");
    modelo3.setTemperaturaMaxima(22F);
    modelo3.setTemperaturaMinima(-5F);

    modelo4 = new Modelo();
    modelo4.setNombre("MK-2655");
    modelo4.setTemperaturaMaxima(16F);
    modelo4.setTemperaturaMinima(-8F);
  }

  static void iniciarOfertas() {
    oferta1 = new Oferta();
    oferta1.setNombre("Termo Stanley");
    oferta1.setPuntosNecesarios(1000F);
    oferta1.setOfertante(elCityGroup);
    oferta1.setDescripcion("Excelente termo para mantener la temperatura de tus bebidas. "
        + "Ideal para llevar a la cancha o a la oficina.");
    oferta1.setImagenIlustrativa("/static-imgs/termo.jpg");

    oferta2 = new Oferta();
    oferta2.setNombre("Desodorante Reaxona");
    oferta2.setPuntosNecesarios(2877.045F);
    oferta2.setOfertante(elCityGroup);
    oferta2.setDescripcion("Desodorante en aerosol para hombre. "
        + "Protección 48 horas. No mancha la ropa y nunca te abandona. A Tinelli le gustó.");
    oferta2.setImagenIlustrativa("/static-imgs/desodorante.jpg");

    oferta3 = new Oferta();
    oferta3.setNombre("TV Noblex 58 pulgadas");
    oferta3.setPuntosNecesarios(45000.500F);
    oferta3.setOfertante(elCityGroup);
    oferta3.setDescripcion("TV Noblex 58 pulgadas. "
        + "Resolución 4K. Conexión a internet. Control remoto con comando de voz. "
        + "Ideal para ver los partidos de la selección y si jugas en la de chile mejor.");
    oferta3.setImagenIlustrativa("/static-imgs/tele.jpg");

    oferta4 = new Oferta();
    oferta4.setNombre("Cazadores de utopías imposibles - Libro");
    oferta4.setPuntosNecesarios(7500.850F);
    oferta4.setOfertante(elCityGroup);
    oferta4.setDescripcion("El profe Gustavo Alfaro es un técnico sudamericano altamente"
        + " preparado, especialista en táctica, detallista y líder natural. "
        + "Desde su inicio en Rafaela, ha escalado la carrera de entrenador, y su trayectoria lo "
        + "llevará a dirigir en el Mundial de Qatar.");
    oferta4.setImagenIlustrativa("/static-imgs/libro_profe_alfaro.jpg");
  }

  static void iniciarSensores() {
    sensorMovimiento1 = new SensorMovimiento();
    sensorMovimiento1.setHeladera(heladera1);

    sensorMovimiento2 = new SensorMovimiento();
    sensorMovimiento2.setHeladera(heladera2);

    sensorMovimiento3 = new SensorMovimiento();
    sensorMovimiento3.setHeladera(heladera3);

    sensorMovimiento4 = new SensorMovimiento();
    sensorMovimiento4.setHeladera(heladeraRota);

    sensorTemperatura1 = new SensorTemperatura();
    sensorTemperatura1.setHeladera(heladera1);

    sensorTemperatura2 = new SensorTemperatura();
    sensorTemperatura2.setHeladera(heladera2);

    sensorTemperatura3 = new SensorTemperatura();
    sensorTemperatura3.setHeladera(heladera3);

    sensorTemperatura4 = new SensorTemperatura();
    sensorTemperatura4.setHeladera(heladeraRota);
  }

  static void iniciarFormularios() {

    lstPreguntas1 = new ArrayList<>();

    opcion1 = new Opcion();
    opcion1.setOpcion("2");

    opcion2 = new Opcion();
    opcion2.setOpcion("3");

    opcion3 = new Opcion();
    opcion3.setOpcion("5");

    respuesta1 = new Respuesta();
    respuesta1.setPregunta(pregunta1);
    respuesta1.setRespuestaSingleChoice(opcion1);

    respuestaFormulario1 = new RespuestaFormulario();
    respuestaFormulario1.setFormulario(formulario1);
    respuestaFormulario1.setColaborador(mati);
    respuestaFormulario1.setDescripcion("tiene 2");
    respuestaFormulario1.setRespuestas(List.of(respuesta1));

    lstOpciones1 = new ArrayList<>();

    lstOpciones1.add(opcion1);
    lstOpciones1.add(opcion2);
    lstOpciones1.add(opcion3);

    pregunta1 = new Pregunta();
    pregunta1.setPregunta("Cuantos mundiales tiene uruguay?");
    pregunta1.setOpciones(lstOpciones1);
    pregunta1.setEsOpcional(true);
    pregunta1.setTipoPregunta(TipoPregunta.MULTIPLE_CHOICE);

    respuesta2 = new Respuesta();
    respuesta2.setPregunta(pregunta2);
    respuesta2.setRespuestaSingleChoice(opcion6);

    respuestaFormulario2 = new RespuestaFormulario();
    respuestaFormulario2.setFormulario(formulario1);
    respuestaFormulario2.setColaborador(augusto);
    respuestaFormulario2.setDescripcion("tiene 700");
    respuestaFormulario2.setRespuestas(List.of(respuesta2));

    opcion4 = new Opcion();
    opcion4.setOpcion("500");

    opcion5 = new Opcion();
    opcion5.setOpcion("600");

    opcion6 = new Opcion();
    opcion6.setOpcion("700");

    lstOpciones2 = new ArrayList<>();

    lstOpciones2.add(opcion4);
    lstOpciones2.add(opcion5);
    lstOpciones2.add(opcion6);

    pregunta2 = new Pregunta();
    pregunta2.setPregunta("Cuantas lineas tiene este test?");
    pregunta2.setOpciones(lstOpciones2);
    pregunta2.setEsOpcional(false);
    pregunta2.setTipoPregunta(TipoPregunta.MULTIPLE_CHOICE);

    respuesta3 = new Respuesta();
    respuesta3.setPregunta(pregunta3);
    respuesta3.setRespuestaSingleChoice(opcion7);

    respuestaFormulario3 = new RespuestaFormulario();
    respuestaFormulario3.setFormulario(formulario1);
    respuestaFormulario3.setColaborador(iniaki);

    opcion7 = new Opcion();
    opcion7.setOpcion("1");

    opcion8 = new Opcion();
    opcion8.setOpcion("2");

    opcion9 = new Opcion();
    opcion9.setOpcion("3");

    lstOpciones3 = new ArrayList<>();

    pregunta3 = new Pregunta();
    pregunta3.setPregunta("Cuantas intercontinentales gano Velez?");
    pregunta3.setOpciones(lstOpciones3);
    pregunta3.setEsOpcional(true);
    pregunta3.setTipoPregunta(TipoPregunta.SINGLE_CHOICE);

    lstPreguntas1.add(pregunta1);
    lstPreguntas1.add(pregunta2);
    lstPreguntas1.add(pregunta3);

    formulario1 = new Formulario();
    formulario1.setNombre("form");
    formulario1.setPreguntas(lstPreguntas1);
  }

  static void iniciarTecnicos() {
    liam = new Tecnico();
    liam.setNombre("Liam");
    liam.setApellido("Wilk");
    liam.setAreaDeCobertura(ciudad3);
    liam.setCuil("1459890002");
    liam.setContacto(contactoLiam);

    santi = new Tecnico();
    santi.setNombre("Santiago");
    santi.setApellido("Mendez");
    santi.setAreaDeCobertura(ciudad1);
    santi.setCuil("1178991234");
    santi.setContacto(contactoSanti);
  }

  static void iniciarSuscripciones() {

    desperfecto = new Desperfecto();
    desperfecto.setHeladera(heladera1);
    desperfecto.setColaborador(iniaki);

    faltanViandas = new FaltanViandas();
    faltanViandas.setHeladera(heladera2);
    faltanViandas.setColaborador(mati);
    faltanViandas.setViandasFaltantes(5);

    quedanViandas = new QuedanViandas();
    quedanViandas.setHeladera(heladera3);
    quedanViandas.setColaborador(augusto);
    quedanViandas.setViandasDisponibles(2);

  }

  static void iniciarViandasPorColaborador() {
    viandasPorColaborador1 = new ViandasPorColaborador(augusto, 11);

    viandasPorColaborador2 = new ViandasPorColaborador(iniaki, 22);

    viandasPorColaborador3 = new ViandasPorColaborador(mati, 33);
  }

  static void iniciarReportes() {
    reporteHeladera1 = new ReporteHeladera(heladera1);
    reporteHeladera1.setFallas(0);
    reporteHeladera1.setViandasRetiradas(5);
    reporteHeladera1.setViandasColocadas(11);
    reporteHeladera1.agregarNuevaColaboracion(viandasPorColaborador1);

    reporteHeladera2 = new ReporteHeladera(heladera2);
    reporteHeladera2.setFallas(0);
    reporteHeladera2.setViandasRetiradas(14);
    reporteHeladera2.setViandasColocadas(24);
    reporteHeladera2.agregarNuevaColaboracion(viandasPorColaborador2);

    reporteHeladera3 = new ReporteHeladera(heladera3);
    reporteHeladera3.setFallas(2);
    reporteHeladera3.setViandasRetiradas(7);
    reporteHeladera3.setViandasColocadas(33);
    reporteHeladera3.agregarNuevaColaboracion(viandasPorColaborador3);

    reporteSemanal1 = new ReporteSemanal("/reportes/reporte-semana-2024-06-16.pdf",
        List.of(reporteHeladera1, reporteHeladera2, reporteHeladera3));
    reporteSemanal1.setNombre("reporte-semana-2024-06-16");
    reporteSemanal1.setFecha(LocalDate.of(2024, 6, 16));

    reporteSemanal2 = new ReporteSemanal("/reportes/reporte-semana-2024-06-22.pdf",
        List.of(reporteHeladera1, reporteHeladera2, reporteHeladera3));
    reporteSemanal2.setNombre("reporte-semana-2024-06-22");
    reporteSemanal2.setFecha(LocalDate.of(2024, 6, 22));

    reporteSemanal3 = new ReporteSemanal("/reportes/reporte-semana-2024-06-27.pdf",
        List.of(reporteHeladera1, reporteHeladera2, reporteHeladera3));
    reporteSemanal3.setNombre("reporte-semana-2024-06-29");
    reporteSemanal3.setFecha(LocalDate.of(2024, 6, 27));
  }

  static void iniciarIncidentes() {
    alerta1 = new Incidente(TipoIncidente.ALERTA, heladeraRota);
    alerta1.setTipoAlerta(TipoEstado.INACTIVA_TEMPERATURA);
    alerta1.setMomentoIncidente(LocalDateTime.of(2024, 6, 16, 12, 0));
  }

  static void iniciarRepos() {

    colaboradoresRepository =
        RepositoryLocator.instanceOf(ColaboradoresRepository.class);

    colaboracionesRepository =
        RepositoryLocator.instanceOf(ColaboracionesRepository.class);

    usosTarjetasVulnerablesRepository =
        RepositoryLocator.instanceOf(UsosTarjetasVulnerablesRepository.class);

    tecnicosRepository =
        RepositoryLocator.instanceOf(TecnicosRepository.class);

    repoGenerico =
        RepositoryLocator.instanceOf(GenericRepository.class);

    reportesRepository =
        RepositoryLocator.instanceOf(ReportesHeladerasRepository.class);

    usuariosRepository =
        RepositoryLocator.instanceOf(UsuariosRepository.class);
  }

}
