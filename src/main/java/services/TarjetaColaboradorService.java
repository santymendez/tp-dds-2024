package services;

import models.entities.direccion.Direccion;
import models.entities.personas.colaborador.Colaborador;
import models.entities.personas.colaborador.TipoColaborador;
import models.entities.personas.tarjetas.colaborador.TarjetaColaborador;
import models.repositories.imp.GenericRepository;

/**
 * Service de tarjetas de colaborador.
 */

public class TarjetaColaboradorService {
  private final GenericRepository genericRepository;

  public TarjetaColaboradorService(GenericRepository genericRepository) {
    this.genericRepository = genericRepository;
  }

  public void crear(Colaborador colaborador, Direccion direccion) {

    if (colaborador.getTipoColaborador() != TipoColaborador.FISICO ||
        !direccion.admiteEnvio()
    ) {
      //El envio es solo para los colaboradores
      return;
    }

    TarjetaColaborador tarjeta = new TarjetaColaborador();
    tarjeta.setColaborador(colaborador);

    this.genericRepository.guardar(tarjeta);
  }
}
