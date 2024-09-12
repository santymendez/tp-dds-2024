package utils.map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import utils.recomendator.entities.Punto;

/**
 * Representa un marcador con un lugar, latitud y longitud.
 * Tiene la capacidad de agregar un nuevo marcador y guardar la información en un archivo JSON.
 */

@Data
public class Mark {
  @JsonProperty("lugar")
  private String lugar;
  @JsonProperty("lat")
  private Double lat;
  @JsonProperty("lng")
  private Double lng;

  private static final String JSON_FILE_PATH = "src/main/resources/map/data.json";

  /**
   * Agrega un nuevo marcador con un lugar, latitud y longitud especificados.
   * Guarda la información del marcador en un archivo JSON.
   *
   * @throws IOException si ocurre un error al guardar la información en un archivo.
   */
  public void agregar(Punto punto) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    File jsonFile = new File(JSON_FILE_PATH);

    // Crear un nuevo marcador
    Map<String, Object> newMarker = new HashMap<>();
    newMarker.put("lugar", lugar);
    newMarker.put("lat", lat);
    newMarker.put("lng", lng);

    // Leer el archivo JSON existente
    List<Map<String, Object>> markers =
        mapper.readValue(jsonFile, new TypeReference<>() {
        });

    // Agregar el nuevo marcador a la lista
    markers.add(newMarker);

    // Escribir la lista actualizada de vuelta al archivo JSON
    mapper.writeValue(jsonFile, markers);
    System.out.println("Marcador agregado correctamente en data.json");
  }
}