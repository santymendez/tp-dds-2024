package utils.helpers;

import brokers.tarjetas.PublicadorSolicitudApertura;
import config.Config;
import dtos.SolicitudAperturaOutputDto;
import models.entities.personas.tarjetas.colaborador.UsoTarjetaColaborador;

/**
 * Helper para la solicitud de apertura.
 */

public class SolicitudAperturaHelper {

  /**
   * Realiza la solicitud de apertura.
   *
   * @param usoTarjetaColaborador uso de tarjeta de colaborador.
   */

  public static void realizarSolicitud(UsoTarjetaColaborador usoTarjetaColaborador) {

    SolicitudAperturaOutputDto solicitudAperturaOutputDto =
        SolicitudAperturaOutputDto.fromUsoTarjeta(usoTarjetaColaborador);

    PublicadorSolicitudApertura publicadorSolicitudApertura = new PublicadorSolicitudApertura();
    publicadorSolicitudApertura.publicarSolicitudEnBroker(solicitudAperturaOutputDto,
            Config.getSolicTopic(), Config.getSolicCli()
    );
  }
}
