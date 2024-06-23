package utils.sender;

import java.util.ArrayList;
import java.util.List;
import models.entities.personas.contacto.TipoContacto;
import utils.sender.channels.EmailSender;
import utils.sender.channels.TelegramBotSender;
import utils.sender.channels.WhatsAppSender;

/**
 * Ejemplo de utilizacion del SenderInterface
 */

public class ExampleSender {

  /**
   * Metodo comentado en el que se ejemplifica el uso de la interfaz sender
   *
   * @param argv argumentos
   * @throws Exception si ocurre algun error
   */

  public static void main(String[] argv) throws Exception {

    //Destinatario destinatario1 = new Destinatario();
    //Destinatario destinatario2 = new Destinatario();
    //Destinatario destinatario3 = new Destinatario();
    //Destinatario destinatario4 = new Destinatario();
    //Destinatario destinatario5 = new Destinatario();

    /*WhatsAppSender whatsAppSender = WhatsAppSender.getInstance();

    String nro1 = "5493388502025"; // augusto
    String nro2 = "5491125168670"; // liam
    String nro3 = "5491157690328"; // iniaki
    String nro4 = "5491138711900"; // santi
    String nro5 = "5491161267515"; // mati

    destinatario1.agregarMedioDeContacto(TipoContacto.WHATSAPP, nro1);
    destinatario2.agregarMedioDeContacto(TipoContacto.WHATSAPP, nro2);
    destinatario3.agregarMedioDeContacto(TipoContacto.WHATSAPP, nro3);
    destinatario4.agregarMedioDeContacto(TipoContacto.WHATSAPP, nro4);
    destinatario5.agregarMedioDeContacto(TipoContacto.WHATSAPP, nro5);

    Mensaje men = new Mensaje("Hola", "mensaje de prueba desde twilio");

    whatsAppSender.enviar(men, destinatario1);
    whatsAppSender.enviar(men, destinatario2);
    whatsAppSender.enviar(men, destinatario3);
    whatsAppSender.enviar(men, destinatario4);
    whatsAppSender.enviar(men, destinatario5);*/

    /*TelegramBotSender telegramBotSender = TelegramBotSender.getInstance();

    String idTelegram1 = "5150752592"; // augusto
    String idTelegram2 = "204391963"; // liam
    String idTelegram3 = "6312955154"; // iniaki
    String idTelegram4 = "7250395212"; // santi
    String idTelegram5 = "6587582903"; // mati

    destinatario1.agregarMedioDeContacto(TipoContacto.TELEGRAM, idTelegram1);
    destinatario2.agregarMedioDeContacto(TipoContacto.TELEGRAM, idTelegram2);
    destinatario3.agregarMedioDeContacto(TipoContacto.TELEGRAM, idTelegram3);
    destinatario4.agregarMedioDeContacto(TipoContacto.TELEGRAM, idTelegram4);
    destinatario5.agregarMedioDeContacto(TipoContacto.TELEGRAM, idTelegram5);

    Mensaje men = new Mensaje("hola", "Mensaje enviado desde java");

    telegramBotSender.enviar(men, destinatario1);
    telegramBotSender.enviar(men, destinatario2);
    telegramBotSender.enviar(men, destinatario3);
    telegramBotSender.enviar(men, destinatario4);
    telegramBotSender.enviar(men, destinatario5);*/

  }

}
