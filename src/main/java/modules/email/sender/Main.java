package modules.email.sender;

public class Main {

  public static void main(String argv[]){
    EmailSender sender = new EmailSender();
    String cuerpo = "¡Hola!";
    Mensaje men = new Mensaje("Informacion Importante", cuerpo);

    sender.enviar(men, "augusto.mazzini@gmail.com");
  }
}
