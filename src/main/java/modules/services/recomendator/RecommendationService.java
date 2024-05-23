package modules.services.recomendator;

import modules.services.recomendator.entities.ListadoDepuntos;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * La API que provee la consultora para la selección de puntos.
 */
public interface RecommendationService {
  @GET("puntos")
  Call<ListadoDepuntos> puntos(
      @Query("lat") int lat,
      @Query("lon") int lon,
      @Query("rad") int rad);
}
