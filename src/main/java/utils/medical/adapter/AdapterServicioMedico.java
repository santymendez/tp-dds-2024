package utils.medical.adapter;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.medical.MedicalService;
import utils.medical.entities.Barrio;

/**
 * Representa a un recomendador de puntos de colocacion para las empresas.
 */

public class AdapterServicioMedico implements InterfaceAdapterServicioMedico {
  private static AdapterServicioMedico instancia = null;
  private static final String urAPI = "http://localhost:4567/";
  private final Retrofit retrofit;

  private AdapterServicioMedico() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urAPI)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  /**
   * Se encarga de devolver el objeto, si no estaba instanciado
   * lo crea.
   *
   * @return ServicioMedico
   */

  public static AdapterServicioMedico getInstancia() {
    if (instancia == null) {
      instancia = new AdapterServicioMedico();
    }
    return instancia;
  }

  /**
   * Realiza una consulta a la API Rest y devuelve un listado de barrios.
   *
   * @return ListadoDeBarrios un listado con los barrios.
   *
   * @throws IOException si ocurre un error lanza una excepción.
   */

  public List<Barrio> barrios() throws IOException {
    MedicalService medicalService = this.retrofit.create(MedicalService.class);
    Call<List<Barrio>> requestBarrios = medicalService.barrios();
    Response<List<Barrio>> responseBarrio = requestBarrios.execute();

    return responseBarrio.body();
  }

}