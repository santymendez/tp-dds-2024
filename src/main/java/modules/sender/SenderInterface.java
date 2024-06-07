package modules.sender;

public interface SenderInterface {

  void enviar(Mensaje men, Destinatario dest);

  /*
  se me ocurrio como que todos debian cumplir una misma interfaz asi podiamos instanciarlos del tipo:
  SenderInterface sender y despues hacer sender.enviar()
  el problema seria que no usan lo mismo para enviar el mensaje, podria ser crear la clase
  destinatario que se instancie con los datos que tenga y lugo en cada enviar se usan los datos
  que se necesiten.
   */
}
