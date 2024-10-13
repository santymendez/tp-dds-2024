package utils.medical;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import utils.medical.entities.Barrio;

/**
 * La API que provee la consultora para el servicio medico.
 */

public interface MedicalService {
  @GET("barriosPrueba")
  Call<List<Barrio>> barrios();
}
