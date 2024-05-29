package services;

import dtos.ColaboradorInputDto;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.reconocimiento.formula.Formula;
import models.entities.personas.contacto.Contacto;
import models.entities.personas.contacto.TipoContacto;
import models.entities.personas.documento.Documento;
import models.entities.personas.documento.TipoDocumento;
import models.repositories.ColaboradoresRepository;
import modules.authentication.Usuario;
import modules.email.sender.EmailSender;
import modules.email.sender.Mensaje;

/**
 * Instancia el colaborador y lo guarda en el repositorio.
 */

public class ColaboradoresService {

  private final ColaboradoresRepository colaboradoresRepository;

  public ColaboradoresService(ColaboradoresRepository colaboradorRepository) {
    this.colaboradoresRepository = colaboradorRepository;
  }

  /**
   * Se cargan los datos a un colaborador.
   *
   * @param colaboradorInputDto Es el input del colaborador.
   */

  public Colaborador crear(ColaboradorInputDto colaboradorInputDto) {

    Colaborador colaborador = new Colaborador();
    colaborador.setNombre(colaboradorInputDto.getNombre());
    colaborador.setApellido(colaboradorInputDto.getApellido());

    Documento documento = new Documento(colaboradorInputDto.getNumeroDocumento(),
        TipoDocumento.valueOf(colaboradorInputDto.getTipoDocumento()));
    colaborador.setDocumento(documento);

    Contacto contacto = new Contacto(colaboradorInputDto.getEmail(), TipoContacto.MAIL);
    colaborador.setContacto(contacto);

    //Crear usuario y enviar mail
    Usuario usuario =  new Usuario(colaborador.getNombre(), colaborador.getApellido());
    colaborador.setUsuario(usuario);

    EmailSender emailsender = EmailSender.getInstance();
    Mensaje message = new Mensaje("Creación de Nuevo Usuario",
        "Se le ha creado un nuevo usuario en el sistema para ingresar. \n"
            + "\nSus credenciales son: \nUsuario: "
            + colaboradorInputDto.getEmail()
            + "\nContraseña: "
            + colaboradorInputDto.getApellido()
            + "\nPuede cambiarlas si así lo desea.\n\nSaludos!");
    emailsender.enviar(message, colaboradorInputDto.getEmail());

    //Para los test, en realidad la formula ya deberia estar creada
    // y solo deberiamos hacer el set al reconocimiento.
    Formula formula = new Formula();
    formula.setCoefPesosDonados(0.5f);
    formula.setCoefViandasDonadas(1.5f);
    formula.setCoefHeladerasActivas(5f);
    formula.setCoefViandasDistribuidas(1.0f);
    formula.setCoefTarjetasRepartidas(2f);
    colaborador.getReconocimiento().setFormulaCalculoDePuntos(formula);

    colaboradoresRepository.guardar(colaborador);

    return colaborador;
  }
}