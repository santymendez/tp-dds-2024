package utils.recomendator;

import utils.recomendator.entities.ListadoPuntos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * La API que provee la consultora para la selección de puntos.
 */
public interface RecommendationService {
  @GET("puntos")
  Call<ListadoPuntos> puntos(
      @Query("lat") String lat,
      @Query("lon") String lon,
      @Query("rad") String rad);
}
