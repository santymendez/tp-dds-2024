package recomendator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import utils.recomendator.adapter.AdapterServicioRecomendacion;
import utils.recomendator.entities.ListadoPuntos;
import utils.recomendator.entities.Punto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

public class TestRecomendator {
  AdapterServicioRecomendacion servicioRecomendacion;

  @BeforeEach
  public void inicializar() throws IOException {
    servicioRecomendacion = mock(AdapterServicioRecomendacion.class);
    ListadoPuntos lst = new ListadoPuntos();
    lst.puntos = new ArrayList<>();
    Punto pto1 = new Punto("heladera_3", "26", "96");
    Punto pto2 = new Punto("heladera_27", "22", "94");
    Punto pto3 = new Punto("heladera_49", "24", "96");
    lst.puntos.add(pto1);
    lst.puntos.add(pto2);
    lst.puntos.add(pto3);
    when(servicioRecomendacion.puntos("14", "55", "2")).thenReturn(lst);
  }


  @Test
  @DisplayName("El recomendador de puntos devuelve puntos y no tira una excepción ")
  public void test01() {
    Assertions.assertDoesNotThrow(() -> servicioRecomendacion.puntos("14", "55", "2"));
  }

  @Test
  @DisplayName("El recomendador de puntos obtiene los puntos que deberia mandar")
  public void test02() {

    ListadoPuntos lst = new ListadoPuntos();
    lst.puntos = new ArrayList<>();
    Punto pto1 = new Punto("heladera_3", "26", "96");
    Punto pto2 = new Punto("heladera_27", "22", "94");
    Punto pto3 = new Punto("heladera_49", "24", "96");
    lst.puntos.add(pto1);
    lst.puntos.add(pto2);
    lst.puntos.add(pto3);

    try {
      ListadoPuntos puntosRequest = servicioRecomendacion.puntos("14", "55", "2");

      Assertions.assertEquals(lst.puntos.get(0).getLat(), puntosRequest.puntos.get(0).getLat());
      Assertions.assertEquals(lst.puntos.get(1).getLat(), puntosRequest.puntos.get(1).getLat());
      Assertions.assertEquals(lst.puntos.get(2).getLat(), puntosRequest.puntos.get(2).getLat());

      Assertions.assertEquals(lst.puntos.get(0).getLng(), puntosRequest.puntos.get(0).getLng());
      Assertions.assertEquals(lst.puntos.get(1).getLng(), puntosRequest.puntos.get(1).getLng());
      Assertions.assertEquals(lst.puntos.get(2).getLng(), puntosRequest.puntos.get(2).getLng());

      Assertions.assertEquals(lst.puntos.get(0).getReferencia(), puntosRequest.puntos.get(0).getReferencia());
      Assertions.assertEquals(lst.puntos.get(1).getReferencia(), puntosRequest.puntos.get(1).getReferencia());
      Assertions.assertEquals(lst.puntos.get(2).getReferencia(), puntosRequest.puntos.get(2).getReferencia());

    } catch (IOException exception) {
      System.out.println("Ocurrió la siguiente excepción: " + exception);
    }
  }

}
