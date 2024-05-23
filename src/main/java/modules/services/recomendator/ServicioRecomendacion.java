package modules.services.recomendator;

import java.io.IOException;
import modules.services.recomendator.entities.ListadoDepuntos;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Representa a un recomendador de puntos de colocacion para las empresas.
 */

public class ServicioRecomendacion {
  private static  ServicioRecomendacion instancia = null;
  private static final String urAPI = "https://71f019a3-8787-49bf-891b-05a9650407ed.mock.pstmn.io/";
  private Retrofit retrofit;


  private ServicioRecomendacion() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urAPI)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  /**
   * se encarga de devolver el objeto, si no estaba instanciado
   * lo crea.
   *
   * @return ServicioRecomendacion
   */


  public static ServicioRecomendacion getInstancia() {
    if (instancia == null) {
      instancia = new ServicioRecomendacion();
    }
    return instancia;
  }

  /**
   * A partir de una latitud, longitud y radio, realiza
   * una consulta a la API Rest y devuelve un listado de puntos.
   *
   * @param lat la latitud
   * @param lon la longitud
   * @param rad el radio
   * @return ListadoDepuntos un listado con los puntos
   * @throws IOException si ocurre un error lanza una excepción.
   */

  public ListadoDepuntos listadoDePuntos(int lat, int lon, int rad) throws IOException {
    RecommendationService recomendationService = this.retrofit.create(RecommendationService.class);
    Call<ListadoDepuntos> requestPuntos = recomendationService.puntos(lat, lon, rad);
    Response<ListadoDepuntos> responsePuntos = requestPuntos.execute();

    return responsePuntos.body();
  }

}