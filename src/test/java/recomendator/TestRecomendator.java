package recomendator;

import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import utils.recomendator.adapter.AdapterServicioRecomendacion;
import utils.recomendator.entities.ListadoPuntos;
import utils.recomendator.entities.Punto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.ArrayList;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AdapterServicioRecomendacion.class) // Prepare the class for PowerMock
public class TestRecomendator {
  private AdapterServicioRecomendacion servicioRecomendacion;

  @BeforeEach
  public void inicializar() {
    servicioRecomendacion = mock(AdapterServicioRecomendacion.class);
    ListadoPuntos lst = new ListadoPuntos();
    lst.puntos = new ArrayList<>();

    Punto pto1 = new Punto("heladera_3", "26", "96");
    Punto pto2 = new Punto("heladera_27", "22", "94");
    Punto pto3 = new Punto("heladera_49", "24", "96");
    lst.puntos.add(pto1);
    lst.puntos.add(pto2);
    lst.puntos.add(pto3);

    try {
      when(servicioRecomendacion.puntos("14", "55", "2")).thenReturn(lst);
    } catch (IOException e) {
      throw new RuntimeException("IOException inesperado durante mockeo", e);
    }
  }

  @Test
  @DisplayName("El recomendador de puntos devuelve puntos y no tira una excepción ")
  public void test01() {
    Assertions.assertDoesNotThrow(() -> servicioRecomendacion.puntos("14", "55", "2"));
  }

  @Test
  @DisplayName("El recomendador de puntos obtiene los puntos que deberia mandar")
  public void test02() {
    ListadoPuntos expectedList = new ListadoPuntos();
    expectedList.puntos = new ArrayList<>();
    expectedList.puntos.add(new Punto("heladera_3", "26", "96"));
    expectedList.puntos.add(new Punto("heladera_27", "22", "94"));
    expectedList.puntos.add(new Punto("heladera_49", "24", "96"));

    ListadoPuntos puntosRequest;
    try {
      puntosRequest = servicioRecomendacion.puntos("14", "55", "2");

      Assertions.assertNotNull(puntosRequest);
      Assertions.assertEquals(expectedList.puntos.size(), puntosRequest.puntos.size());

      for (int i = 0; i < expectedList.puntos.size(); i++) {
        Assertions.assertEquals(expectedList.puntos.get(i).getLat(), puntosRequest.puntos.get(i).getLat());
        Assertions.assertEquals(expectedList.puntos.get(i).getLng(), puntosRequest.puntos.get(i).getLng());
        Assertions.assertEquals(expectedList.puntos.get(i).getReferencia(), puntosRequest.puntos.get(i).getReferencia());
      }
    } catch (IOException e) {
      Assertions.fail("IOException arrojado durante ejecución: " + e.getMessage());
    }
  }
}