package recomendator;

import modules.recomendator.adapter.AdapterServicioRecomendacion;
import modules.recomendator.entities.ListadoDepuntos;
import modules.recomendator.entities.Punto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

public class TestRecomendator {
  AdapterServicioRecomendacion servicioRecomendacion;

  @BeforeEach
  public void inicializar(){
   servicioRecomendacion = AdapterServicioRecomendacion.getInstancia();
  }


  @Test
  @DisplayName("El recomendador de puntos devuelve puntos y no tira una excepción ")
  public void test01() {
    Assertions.assertDoesNotThrow(() -> servicioRecomendacion.listadoDePuntos(14, 55, 2));
  }

  @Test
  @DisplayName("El recomendador de puntos obtiene los puntos que deberia mandar")
  public void test02() {

    ListadoDepuntos lst = new ListadoDepuntos();
    lst.puntos = new ArrayList<>();
    Punto pto1 = new Punto(26, 96, "heladera_3");
    Punto pto2 = new Punto(22, 92, "heladera_27");
    Punto pto3 = new Punto(24, 96, "heladera_49");
    lst.puntos.add(pto1);
    lst.puntos.add(pto2);
    lst.puntos.add(pto3);

    try {
      ListadoDepuntos puntosRequest = servicioRecomendacion.listadoDePuntos(14, 55, 2);

      Assertions.assertEquals(lst.puntos.get(0).getLat(), puntosRequest.puntos.get(0).getLat());
      Assertions.assertEquals(lst.puntos.get(1).getLat(), puntosRequest.puntos.get(1).getLat());
      Assertions.assertEquals(lst.puntos.get(2).getLat(), puntosRequest.puntos.get(2).getLat());

      Assertions.assertEquals(lst.puntos.get(0).getLon(), puntosRequest.puntos.get(0).getLon());
      Assertions.assertEquals(lst.puntos.get(1).getLon(), puntosRequest.puntos.get(1).getLon());
      Assertions.assertEquals(lst.puntos.get(2).getLon(), puntosRequest.puntos.get(2).getLon());

      Assertions.assertEquals(lst.puntos.get(0).getReferencia(), puntosRequest.puntos.get(0).getReferencia());
      Assertions.assertEquals(lst.puntos.get(1).getReferencia(), puntosRequest.puntos.get(1).getReferencia());
      Assertions.assertEquals(lst.puntos.get(2).getReferencia(), puntosRequest.puntos.get(2).getReferencia());

    } catch (IOException exception) {
      System.out.println("Ocurrió la siguiente excepción: " + exception);
    }
  }

}
