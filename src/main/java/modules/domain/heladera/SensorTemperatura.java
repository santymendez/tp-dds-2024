package modules.domain.heladera;

import lombok.Getter;
import lombok.Setter;

/**
 * Representa un sensor de temperatura con la ultima temperatura, las temperaturas maximas
 * y minimas.
 */

@Getter
@Setter
public class SensorTemperatura {
  private Float temperaturaMinima;
  private Float temperaturaMaxima;
  private Float ultimaTemperatura;

  public Boolean estaActiva() {

    return ultimaTemperatura < temperaturaMaxima && ultimaTemperatura > temperaturaMinima;
  }
}

 /*
import java.util.Timer;
import java.util.TimerTask;

public class MiClase {
    public static void main(String[] args) {
        Timer timer = new Timer();

        // Define la tarea que se ejecutará periódicamente
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Coloca aquí el código que deseas ejecutar cada cierto intervalo
                System.out.println("Ejecutando tarea...");
            }
        };

        // Programa la tarea para que se ejecute cada 5 segundos (5000 milisegundos)
        timer.scheduleAtFixedRate(task, 0, 5000);
    }
}


 */