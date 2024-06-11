package utils.sender;

/**
 * Interfaz para los senders.
 */

// TODO la interfaz no la estan implementando ni mail ni whatsapp ni telegram
public interface SenderInterface {

  SenderInterface getInstance();

  void enviar(Mensaje men, Destinatario dest);

}
