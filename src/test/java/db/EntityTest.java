package db;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import models.db.PersistenceUnitSwitcher;
import models.entities.colaboracion.Colaboracion;
import models.entities.colaboracion.ColocacionHeladera;
import models.entities.colaboracion.DistribucionTarjetas;
import models.entities.colaboracion.DistribucionViandas;
import models.entities.colaboracion.DonacionDinero;
import models.entities.colaboracion.RealizacionOfertas;
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
import models.entities.heladera.sensores.movimiento.SensorMovimiento;
import models.entities.heladera.sensores.temperatura.SensorTemperatura;
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
import models.entities.personas.colaborador.suscripcion.TipoSuscripcion;
import models.entities.personas.contacto.Contacto;
import models.entities.personas.contacto.TipoContacto;
import models.entities.personas.documento.Documento;
import models.entities.personas.documento.TipoDocumento;
import models.entities.personas.tarjetas.vulnerable.RegistroVulnerable;
import models.entities.personas.tarjetas.vulnerable.TarjetaVulnerable;
import models.entities.personas.tecnico.Tecnico;
import models.entities.personas.vulnerable.Vulnerable;
import models.entities.reporte.ReporteHeladera;
import models.entities.reporte.ViandasPorColaborador;
import models.repositories.RepositoryLocator;
import models.repositories.imp.ColaboracionesRepository;
import models.repositories.imp.ColaboradoresRepository;
import models.repositories.imp.GenericRepository;
import models.repositories.imp.ReportesRepository;
import models.repositories.imp.TecnicosRepository;
import models.repositories.imp.UsosTarjetasVulnerablesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.sender.Mensaje;
import utils.sender.channels.EmailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class EntityTest {

  ColaboradoresRepository colaboradoresRepository;

  ColaboracionesRepository colaboracionesRepository;

  UsosTarjetasVulnerablesRepository usosTarjetasVulnerablesRepository;

  TecnicosRepository tecnicosRepository;

  GenericRepository repoGenerico;

  ReportesRepository reportesRepository;

  EmailSender emailSender = mock(EmailSender.class);

  ColocacionHeladera colocacionHeladera;
  Colaboracion colocarHeladera;

  DistribucionTarjetas distribucionTarjetas;
  TarjetaVulnerable tarjeta1;
  TarjetaVulnerable tarjeta2;
  TarjetaVulnerable tarjeta3;
  List<TarjetaVulnerable> lstTarjetas;
  Colaboracion distribuirTarjetas;

  DistribucionViandas distribucionViandas;
  Colaboracion distribuirViandas;

  DonacionDinero donacionDinero;
  Colaboracion donarDinero;

  RealizacionOfertas realizarOfertas;
  List<Oferta> lstOfertas;
  Oferta oferta1;
  Oferta oferta2;
  Oferta oferta3;
  Colaboracion realizarOferta;

  Direccion direccion1;
  Direccion direccion2;
  Direccion direccion3;

  Barrio barrio1;
  Barrio barrio2;
  Barrio barrio3;

  Ciudad ciudad1;
  Ciudad ciudad2;
  Ciudad ciudad3;

  Provincia provincia1;
  Provincia provincia2;
  Provincia provincia3;

  Formulario formulario1;

  List<Pregunta> lstPreguntas1;

  Pregunta pregunta1;
  Pregunta pregunta2;
  Pregunta pregunta3;

  List<Opcion> lstOpciones1;
  List<Opcion> lstOpciones2;
  List<Opcion> lstOpciones3;

  Opcion opcion1;
  Opcion opcion2;
  Opcion opcion3;
  Opcion opcion4;
  Opcion opcion5;
  Opcion opcion6;
  Opcion opcion7;
  Opcion opcion8;
  Opcion opcion9;

  Respuesta respuesta1;
  Respuesta respuesta2;
  Respuesta respuesta3;

  RespuestaFormulario respuestaFormulario1;
  RespuestaFormulario respuestaFormulario2;
  RespuestaFormulario respuestaFormulario3;

  Heladera heladera1;
  Heladera heladera2;
  Heladera heladera3;

  Estado estadoActual1;
  Estado estadoActual2;
  Estado estadoActual3;

  List<Estado> lstEstados1;
  List<Estado> lstEstados2;
  List<Estado> lstEstados3;

  Estado estado1;
  Estado estado2;
  Estado estado3;
  Estado estado4;
  Estado estado5;

  SensorMovimiento sensorMovimiento1;
  SensorMovimiento sensorMovimiento2;
  SensorMovimiento sensorMovimiento3;

  SensorTemperatura sensorTemperatura1;
  SensorTemperatura sensorTemperatura2;
  SensorTemperatura sensorTemperatura3;

  List<Vianda> lstViandas1;
  List<Vianda> lstViandas2;
  List<Vianda> lstViandas3;

  Vianda vianda1;
  Vianda vianda2;
  Vianda vianda3;
  Vianda vianda4;
  Vianda vianda5;
  Vianda vianda6;
  Vianda vianda7;
  Vianda vianda8;
  Vianda vianda9;

  Comida comida1;
  Comida comida2;
  Comida comida3;
  Comida comida4;
  Comida comida5;
  Comida comida6;
  Comida comida7;
  Comida comida8;
  Comida comida9;

  Modelo modelo1;
  Modelo modelo2;
  Modelo modelo3;

  Documento documentoAugusto;

  Formula formula;

  Reconocimiento reconocimientoAugusto;
  Reconocimiento reconocimientoIniaqui;
  Reconocimiento reconocimientoMati;

  Contacto contactoAugusto;
  Contacto contactoIniaqui;
  Contacto contactoMati;
  Contacto contactoCitiGroup;
  Contacto contactoLiam;
  Contacto contactoSanti;

  Colaborador augusto;
  Colaborador iniaki;
  Colaborador mati;
  Colaborador elCityGroup;

  Desperfecto desperfecto;
  FaltanViandas faltanViandas;
  QuedanViandas quedanViandas;

  Tecnico liam;
  Tecnico santi;

  Vulnerable eze;
  Vulnerable facu;

  // Vulnerables a cargo de eze
  List<Vulnerable> lstMenoresACargoEze;
  Vulnerable perez;
  Vulnerable tello;
  Vulnerable enrique;

  // Vulnerables a cargo de facu
  List<Vulnerable> lstMenoresACargoFacu;
  Vulnerable villalva;

  RegistroVulnerable registroVulnerable1;
  RegistroVulnerable registroVulnerable2;
  RegistroVulnerable registroVulnerable3;

  ReporteHeladera reporteHeladera1;
  ReporteHeladera reporteHeladera2;
  ReporteHeladera reporteHeladera3;

  ViandasPorColaborador viandasPorColaborador1;
  ViandasPorColaborador viandasPorColaborador2;
  ViandasPorColaborador viandasPorColaborador3;

  @BeforeEach
  void inicializarObjetos() {
    //Para testear en db poner database en  vez de simple
    PersistenceUnitSwitcher.switchPersistenceUnit("simple-persistence-unit");

    this.iniciarContactos();
    this.iniciarColaboradores();
    this.iniciarVulnerables();
    this.iniciarProvincias();
    this.iniciarCiudades();
    this.iniciarBarrios();
    this.iniciarDirecciones();
    this.iniciarEstados();
    this.iniciarModelos();
    this.iniciarSensores();
    this.iniciarHeladeras();
    this.iniciarViandas();
    this.iniciarRegistrosVulnerables();
    this.iniciarTarjetas();
    this.iniciarOfertas();
    this.iniciarColaboraciones();
    this.iniciarTecnicos();
    this.iniciarFormularios();
    this.iniciarSuscripciones();
    this.iniciarViandasPorColaborador();
    this.iniciarReportes();

    doNothing().when(emailSender).enviar(any(Mensaje.class), any(String.class));
  }

  void iniciarContactos() {
    contactoAugusto = new Contacto("+54 9 11 1234-5678", TipoContacto.WHATSAPP);
    contactoIniaqui = new Contacto("54645213212", TipoContacto.TELEGRAM);
    contactoMati = new Contacto("contactofalso@gmail.com", TipoContacto.MAIL);
    contactoCitiGroup = new Contacto("citigroup@hdp.com", TipoContacto.MAIL);
    contactoLiam = new Contacto("liam@gmail.com", TipoContacto.MAIL);
    contactoSanti = new Contacto("santi@ghost.com", TipoContacto.MAIL);
  }

  void iniciarColaboradores() {

    formula = new Formula();

    reconocimientoAugusto = new Reconocimiento();
    reconocimientoAugusto.setFormulaCalculoDePuntos(formula);

    reconocimientoIniaqui = new Reconocimiento();
    reconocimientoIniaqui.setFormulaCalculoDePuntos(formula);

    reconocimientoMati = new Reconocimiento();
    reconocimientoMati.setFormulaCalculoDePuntos(formula);

    documentoAugusto = new Documento(45345678, TipoDocumento.DNI);

    augusto = new Colaborador();
    augusto.setNombre("Augusto");
    augusto.setApellido("Mazzini");
    augusto.setDocumento(documentoAugusto);
    augusto.setContacto(contactoAugusto);
    augusto.setTipoColaborador(TipoColaborador.FISICO);
    augusto.setReconocimiento(reconocimientoAugusto);

    iniaki = new Colaborador();
    iniaki.setNombre("Iñaki");
    iniaki.setApellido("Lorences");
    iniaki.setContacto(contactoIniaqui);
    iniaki.setTipoColaborador(TipoColaborador.FISICO);
    iniaki.setReconocimiento(reconocimientoIniaqui);

    mati = new Colaborador();
    mati.setNombre("Matias");
    mati.setApellido("Jastrebow");
    mati.setContacto(contactoMati);
    mati.setTipoColaborador(TipoColaborador.FISICO);
    mati.setReconocimiento(reconocimientoMati);

    elCityGroup = new Colaborador();
    elCityGroup.setNombre("CityGroup");
    elCityGroup.setRazonSocial("El City Group PAPA.SA");
    elCityGroup.setTipoColaborador(TipoColaborador.EMPRESA_ASOCIADA);
    elCityGroup.setContacto(contactoCitiGroup);
  }

  void iniciarVulnerables() {

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

  void iniciarColaboraciones() {

    // Colaboracion - Colocar Heladera

    colocacionHeladera = new ColocacionHeladera();
    colocacionHeladera.setHeladeraColocada(heladera2);

    colocarHeladera = new Colaboracion();
    colocarHeladera.setFechaColaboracion(LocalDate.of(2021, 10, 10));
    colocarHeladera.setColocacionHeladera(colocacionHeladera);
    colocarHeladera.setTipoColaboracion(TipoColaboracion.COLOCAR_HELADERA);
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
    donacionDinero.setFrecuenciaDonacion("Casi nunca, tampoco es millonario");

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

    realizarOfertas = new RealizacionOfertas();
    realizarOfertas.setOfertasRealizadas(lstOfertas);

    realizarOferta = new Colaboracion();
    realizarOferta.setColaborador(elCityGroup);
    realizarOferta.setTipoColaboracion(TipoColaboracion.REALIZAR_OFERTAS);
    realizarOferta.setFechaColaboracion(LocalDate.of(2012, 7, 27));
    realizarOferta.setRealizarOfertas(realizarOfertas);
  }

  void iniciarTarjetas() {

    lstTarjetas = new ArrayList<>();

    tarjeta1 = new TarjetaVulnerable(registroVulnerable1);

    tarjeta2 = new TarjetaVulnerable(registroVulnerable2);

    tarjeta3 = new TarjetaVulnerable(registroVulnerable3);

    lstTarjetas.add(tarjeta1);
    lstTarjetas.add(tarjeta2);
    lstTarjetas.add(tarjeta3);
  }

  void iniciarRegistrosVulnerables() {

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

  void iniciarHeladeras() {

    heladera1 = new Heladera();
    heladera1.setFechaDeCreacion(LocalDate.now());
    heladera1.setEstadoActual(estadoActual1);
    heladera1.setDireccion(direccion1);
    heladera1.setNombre("Heladera Porteña");
    heladera1.setEstadosHeladera(lstEstados1);
    heladera1.setModelo(modelo1);
    heladera1.setCapacidadMaximaViandas(18);

    heladera2 = new Heladera();
    heladera2.setFechaDeCreacion(LocalDate.now());
    heladera2.setEstadoActual(estadoActual2);
    heladera2.setDireccion(direccion2);
    heladera2.setNombre("Heladera Cordobesa");
    heladera2.setEstadosHeladera(lstEstados2);
    heladera2.setModelo(modelo2);
    heladera2.setCapacidadMaximaViandas(25);

    heladera3 = new Heladera();
    heladera3.setFechaDeCreacion(LocalDate.now());
    heladera3.setEstadoActual(estadoActual3);
    heladera3.setDireccion(direccion3);
    heladera3.setNombre("Heladera Santiagueña");
    heladera3.setEstadosHeladera(lstEstados3);
    heladera3.setModelo(modelo3);
    heladera3.setCapacidadMaximaViandas(4);
  }

  void iniciarViandas() {
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

  void iniciarDirecciones() {

    direccion1 = new Direccion();
    direccion1.setBarrio(barrio1);
    direccion1.setNombreUbicacion("Segurola 4310, Villa Devoto, CABA");
    direccion1.setLongitud(-58.517341F);
    direccion1.setLatitud(-34.605857F);

    direccion2 = new Direccion();
    direccion2.setBarrio(barrio2);
    direccion2.setNombreUbicacion("Ruy Díaz de Guzmán 675, Villa Alem, Rio Cuarto");
    direccion2.setLongitud(-64.353041F);
    direccion2.setLatitud(-33.136980F);

    direccion3 = new Direccion();
    direccion3.setBarrio(barrio3);
    direccion3.setNombreUbicacion("Lavalle 800, Villa Griselda, La Banda");
    direccion3.setLongitud(-64.239235F);
    direccion3.setLatitud(-27.732836F);
  }

  void iniciarBarrios() {

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
  }

  void iniciarCiudades() {

    ciudad1 = new Ciudad();
    ciudad1.setProvincia(provincia1);
    ciudad1.setNombreCiudad("CABA");

    ciudad2 = new Ciudad();
    ciudad2.setProvincia(provincia2);
    ciudad2.setNombreCiudad("Rio cuarto");

    ciudad3 = new Ciudad();
    ciudad3.setProvincia(provincia3);
    ciudad3.setNombreCiudad ("La Banda");
  }

  void iniciarProvincias() {
    provincia1 = new Provincia();
    provincia1.setNombreProvincia("Buenos Aires");

    provincia2 = new Provincia();
    provincia2.setNombreProvincia("Cordoba");

    provincia3 = new Provincia();
    provincia3.setNombreProvincia("Santiago del Estero");
  }

  void iniciarEstados() {

    this.estadoActual1 = new Estado();
    estadoActual1.setEstado(TipoEstado.ACTIVA);
    estadoActual1.setFechaInicial(LocalDate.of(2021, 10, 10));
    estadoActual1.setFechaFinal(LocalDate.of(2021, 10, 11));

    this.estadoActual2 = new Estado();
    estadoActual2.setEstado(TipoEstado.ACTIVA);
    estadoActual2.setFechaInicial(LocalDate.of(2021, 11, 12));
    estadoActual2.setFechaFinal(LocalDate.of(2021, 11, 13));

    this.estadoActual3 = new Estado();
    estadoActual3.setEstado(TipoEstado.ACTIVA);
    estadoActual3.setFechaInicial(LocalDate.of(2021, 12, 14));
    estadoActual3.setFechaFinal(LocalDate.of(2021, 12, 15));

    lstEstados1 = new ArrayList<>();
    lstEstados2 = new ArrayList<>();
    lstEstados3 = new ArrayList<>();

    estado1 = new Estado();
    estado1.setEstado(TipoEstado.ACTIVA);
    estado1.setFechaInicial(LocalDate.of(2021, 10, 10));
    estado1.setFechaFinal(LocalDate.of(2021, 10, 11));
    lstEstados1.add(estado1);

    estado2 = new Estado();
    estado2.setEstado(TipoEstado.INACTIVA_FUNCIONAL);
    estado2.setFechaInicial(LocalDate.of(2021, 11, 12));
    estado2.setFechaFinal(LocalDate.of(2021, 11, 13));
    lstEstados2.add(estado2);

    estado3 = new Estado();
    estado3.setEstado(TipoEstado.INACTIVA_TEMPERATURA);
    estado3.setFechaInicial(LocalDate.of(2021, 12, 14));
    estado3.setFechaFinal(LocalDate.of(2021, 12, 15));
    lstEstados3.add(estado3);

    estado4 = new Estado();
    estado4.setEstado(TipoEstado.ACTIVA);
    estado4.setFechaInicial(LocalDate.of(2022, 1, 16));
    estado4.setFechaFinal(LocalDate.of(2022, 1, 17));
    lstEstados1.add(estado4);

    estado5 = new Estado();
    estado5.setEstado(TipoEstado.INACTIVA_FRAUDE);
    estado5.setFechaInicial(LocalDate.of(2022, 2, 18));
    estado5.setFechaFinal(LocalDate.of(2022, 2, 19));
    lstEstados2.add(estado5);
  }

  void iniciarModelos() {
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
  }

  void iniciarOfertas(){
    lstOfertas = new ArrayList<>();

    oferta1 = new Oferta();
    oferta1.setNombre("Oferton");
    oferta1.setPuntosNecesarios(1000F);
    oferta1.setOfertante(elCityGroup);

    oferta2 = new Oferta();
    oferta2.setNombre("Estafa ponzi");
    oferta2.setPuntosNecesarios(2877.045F);
    oferta2.setOfertante(elCityGroup);

    oferta3 = new Oferta();
    oferta3.setNombre("Esta no le gusta al chavo fucks");
    oferta3.setPuntosNecesarios(45000.500F);
    oferta3.setOfertante(elCityGroup);

    lstOfertas.add(oferta1);
    lstOfertas.add(oferta2);
    lstOfertas.add(oferta3);
  }

  void iniciarSensores() {
    sensorMovimiento1 = new SensorMovimiento();
    sensorMovimiento1.setHeladera(heladera1);

    sensorMovimiento2 = new SensorMovimiento();
    sensorMovimiento2.setHeladera(heladera2);

    sensorMovimiento3 = new SensorMovimiento();
    sensorMovimiento3.setHeladera(heladera3);

    sensorTemperatura1 = new SensorTemperatura();
    sensorTemperatura1.setHeladera(heladera1);

    sensorTemperatura2 = new SensorTemperatura();
    sensorTemperatura2.setHeladera(heladera2);

    sensorTemperatura3 = new SensorTemperatura();
    sensorTemperatura3.setHeladera(heladera3);
  }

  void iniciarFormularios() {

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
    pregunta1.setTipoDeSuRespuesta(TipoPregunta.MULTIPLE_CHOICE);

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
    pregunta2.setTipoDeSuRespuesta(TipoPregunta.MULTIPLE_CHOICE);

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
    pregunta3.setTipoDeSuRespuesta(TipoPregunta.SINGLE_CHOICE);

    lstPreguntas1.add(pregunta1);
    lstPreguntas1.add(pregunta2);
    lstPreguntas1.add(pregunta3);

    formulario1 = new Formulario();
    formulario1.setNombre("form");
    formulario1.setPreguntas(lstPreguntas1);
  }

  void iniciarTecnicos(){
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

  void iniciarSuscripciones() {
    desperfecto = new Desperfecto();
    desperfecto.setHeladera(heladera1);
    desperfecto.setColaborador(iniaki);
    desperfecto.setSenderInterface(emailSender);
    desperfecto.setTipo(TipoSuscripcion.OCURRIO_DESPERFECTO);

    faltanViandas = new FaltanViandas();
    faltanViandas.setHeladera(heladera2);
    faltanViandas.setColaborador(mati);
    faltanViandas.setViandasFaltantes(5);
    faltanViandas.setSenderInterface(emailSender);
    faltanViandas.setTipo(TipoSuscripcion.FALTAN_N_VIANDAS);

    quedanViandas = new QuedanViandas();
    quedanViandas.setHeladera(heladera3);
    quedanViandas.setColaborador(augusto);
    quedanViandas.setViandasDisponibles(2);
    quedanViandas.setSenderInterface(emailSender);
    quedanViandas.setTipo(TipoSuscripcion.QUEDAN_N_VIANDAS);

  }

  void iniciarViandasPorColaborador() {
    viandasPorColaborador1 = new ViandasPorColaborador(augusto, 11);

    viandasPorColaborador2 = new ViandasPorColaborador(iniaki, 22);

    viandasPorColaborador3 = new ViandasPorColaborador(mati, 33);
  }

  void iniciarReportes() {
    reporteHeladera1 = new ReporteHeladera(heladera1);
    reporteHeladera1.setPath("/home/usuario/reporte1.pdf");
    reporteHeladera1.setFallas(0);
    reporteHeladera1.setViandasRetiradas(5);
    reporteHeladera1.setViandasColocadas(11);
    reporteHeladera1.agregarNuevaColaboracion(viandasPorColaborador1);

    reporteHeladera2 = new ReporteHeladera(heladera2);
    reporteHeladera2.setPath("/home/usuario/reporte2.pdf");
    reporteHeladera2.setFallas(1);
    reporteHeladera2.setViandasRetiradas(5);
    reporteHeladera2.setViandasColocadas(22);
    reporteHeladera2.agregarNuevaColaboracion(viandasPorColaborador2);

    reporteHeladera3 = new ReporteHeladera(heladera3);
    reporteHeladera3.setPath("/home/usuario/reporte3.pdf");
    reporteHeladera3.setFallas(2);
    reporteHeladera3.setViandasRetiradas(7);
    reporteHeladera3.setViandasColocadas(33);
    reporteHeladera3.agregarNuevaColaboracion(viandasPorColaborador3);
  }

  void iniciarRepos() {

    this.colaboradoresRepository =
        RepositoryLocator.get("colaboradoresRepository", ColaboradoresRepository.class);

    this.colaboracionesRepository =
        RepositoryLocator.get("colaboracionesRepository", ColaboracionesRepository.class);

    this.usosTarjetasVulnerablesRepository =
        RepositoryLocator.get("usosTarjetasVulnerablesRepository", UsosTarjetasVulnerablesRepository.class);

    this.tecnicosRepository =
        RepositoryLocator.get("tecnicosRepository", TecnicosRepository.class);

    this.repoGenerico =
        RepositoryLocator.get("genericRepository", GenericRepository.class);

    this.reportesRepository =
        RepositoryLocator.get("reportesRepository", ReportesRepository.class);
  }

  void peristirEntidades(){
    this.iniciarRepos();

    this.repoGenerico.guardar(direccion1);
    this.repoGenerico.guardar(direccion2);
    this.repoGenerico.guardar(direccion3);

    this.colaboradoresRepository.guardar(augusto);
    this.colaboradoresRepository.guardar(iniaki);
    this.colaboradoresRepository.guardar(mati);
    this.colaboradoresRepository.guardar(elCityGroup);

    this.repoGenerico.guardar(heladera1);
    this.repoGenerico.guardar(heladera2);
    this.repoGenerico.guardar(heladera3);

    this.repoGenerico.guardar(villalva);
    this.repoGenerico.guardar(enrique);
    this.repoGenerico.guardar(perez);
    this.repoGenerico.guardar(tello);
    this.repoGenerico.guardar(eze);
    this.repoGenerico.guardar(facu);

    this.repoGenerico.guardar(desperfecto);
    this.repoGenerico.guardar(faltanViandas);
    this.repoGenerico.guardar(quedanViandas);

    this.repoGenerico.guardar(registroVulnerable1);
    this.repoGenerico.guardar(registroVulnerable2);
    this.repoGenerico.guardar(registroVulnerable3);

    this.colaboracionesRepository.guardar(colocarHeladera);
    this.colaboracionesRepository.guardar(distribuirTarjetas);
    this.colaboracionesRepository.guardar(distribuirViandas);
    this.colaboracionesRepository.guardar(donarDinero);
    this.colaboracionesRepository.guardar(realizarOferta);

    this.tecnicosRepository.guardar(liam);
    this.tecnicosRepository.guardar(santi);

    this.repoGenerico.guardar(formulario1);

    this.repoGenerico.guardar(viandasPorColaborador1);
    this.repoGenerico.guardar(viandasPorColaborador2);
    this.repoGenerico.guardar(viandasPorColaborador3);

    this.reportesRepository.guardar(reporteHeladera1);
    this.reportesRepository.guardar(reporteHeladera2);
    this.reportesRepository.guardar(reporteHeladera3);

  }

  @Test
  @DisplayName("Se pueden guardar y recuperar las entidades en la base de datos")
  void persistirTodo() {
    this.peristirEntidades();

    // Recupero al tecnico sin ningun problema

    Optional<Tecnico> tecnico = tecnicosRepository.buscarPorId(liam.getId());

    Assertions.assertTrue(tecnico.isPresent());

    // Recupero al colaborador sin ningun problema

    Optional<Colaborador> colaborador = colaboradoresRepository
        .buscarPorDocumento(augusto.getDocumento().getNroDocumento());

    Assertions.assertEquals(colaborador.get().getNombre(), "Augusto");

    Assertions.assertEquals(colaborador.get().getDocumento().getTipoDocumento(),
        TipoDocumento.DNI);

    // Recupero al vulnerable sin ningun problema

    Optional<Vulnerable> vulnerable = repoGenerico.buscarPorId(eze.getId(), Vulnerable.class);

    Assertions.assertTrue(vulnerable.isPresent());

    // Recupero la heladera sin ningun problema

    Optional<Heladera> heladera = repoGenerico.buscarPorId(heladera1.getId(), Heladera.class);

    Assertions.assertTrue(heladera.isPresent());

    // Recupero la direccion sin ningun problema

    Optional<Direccion> direccion = repoGenerico.buscarPorId(direccion1.getId(), Direccion.class);

    Assertions.assertTrue(direccion.isPresent());

    // Recupero la provincia sin ningun problema

    Optional<Provincia> provincia = repoGenerico.buscarPorId(provincia1.getId(), Provincia.class);

    // Recupero una colaboracion sin ningun problema

    List<Colaboracion> colaboraciones =
        colaboracionesRepository.buscarPorTipo(TipoColaboracion.COLOCAR_HELADERA);

    Colaboracion colaboracion = colaboraciones.get(0);

    Assertions.assertEquals(colaboracion.getTipoColaboracion(),
        TipoColaboracion.COLOCAR_HELADERA);

    Assertions.assertEquals(colaboracion.getColaborador().getNombre(), "Augusto");

    Assertions.assertEquals(colaboracion.getFechaColaboracion(),
        colocarHeladera.getFechaColaboracion());

  }
}
