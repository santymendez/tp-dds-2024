package utils.recomendator.adapter;

import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.recomendator.RecommendationService;
import utils.recomendator.entities.ListadoPuntos;

/**
 * Representa a un recomendador de puntos de colocacion para las empresas.
 */

public class AdapterServicioRecomendacion implements InterfaceAdapterServicioRecomendacion {
  private static AdapterServicioRecomendacion instancia = null;
  private static final String urAPI = "https://8d039fc3-53c7-4579-95cd-5cdfd50aff50.mock.pstmn.io/";
  private final Retrofit retrofit;

  /**
   * Constructor para AdapterServicioRecomendacion.
   */

  public AdapterServicioRecomendacion() {
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
   * @param lng la longitud
   * @param rad el radio
   * @return ListadoDepuntos un listado con los puntos
   * @throws IOException si ocurre un error lanza una excepción.
   */

  public ListadoPuntos puntos(String lat, String lng, String rad) throws IOException {
    RecommendationService recomendationService = this.retrofit.create(RecommendationService.class);
    Call<ListadoPuntos> requestPuntos = recomendationService.puntos(lat, lng, rad);
    Response<ListadoPuntos> responsePuntos = requestPuntos.execute();

    return responsePuntos.body();
  }

}