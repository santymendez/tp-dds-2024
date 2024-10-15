package utils.helpers;

import brokers.tarjetas.PublicadorSolicitudApertura;
import config.Config;
import dtos.SolicitudAperturaOutputDto;
import models.entities.heladera.Heladera;
import models.entities.personas.tarjetas.colaborador.TarjetaColaborador;
import models.entities.personas.tarjetas.colaborador.UsoTarjetaColaborador;

/**
 * Helper para la solicitud de apertura.
 */

public class SolicitudAperturaHelper {

  /**
   * Realiza la solicitud de apertura.
   *
   * @param tarjetaColaborador tarjeta del colaborador.
   * @param heladera           heladera.
   */

  public static void realizarSolicitud(TarjetaColaborador tarjetaColaborador, Heladera heladera) {
    UsoTarjetaColaborador usoTarjetaColaborador = new UsoTarjetaColaborador();
    tarjetaColaborador.agregarUso(usoTarjetaColaborador, heladera);

    SolicitudAperturaOutputDto solicitudAperturaOutputDto =
        SolicitudAperturaOutputDto.fromUsoTarjeta(usoTarjetaColaborador);

    PublicadorSolicitudApertura publicadorSolicitudApertura = new PublicadorSolicitudApertura();
    publicadorSolicitudApertura.publicarSolicitudEnBroker(solicitudAperturaOutputDto,
            Config.getSolicTopic(), Config.getSolicCli()
    );
  }
}
