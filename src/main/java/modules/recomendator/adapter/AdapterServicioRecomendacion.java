package modules.recomendator.adapter;

import java.io.IOException;
import modules.recomendator.RecommendationService;
import modules.recomendator.entities.ListadoDepuntos;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Representa a un recomendador de puntos de colocacion para las empresas.
 */

public class AdapterServicioRecomendacion implements InterfaceAdapterServicioRecomendacion {
  private static AdapterServicioRecomendacion instancia = null;
  private static final String urAPI = "https://71f019a3-8787-49bf-891b-05a9650407ed.mock.pstmn.io/";
  private final Retrofit retrofit;

  private AdapterServicioRecomendacion() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urAPI)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  /**
   * Se encarga de devolver el objeto, si no estaba instanciado
   * lo crea.
   *
   * @return ServicioRecomendacion
   */

  public static AdapterServicioRecomendacion getInstancia() {
    if (instancia == null) {
      instancia = new AdapterServicioRecomendacion();
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