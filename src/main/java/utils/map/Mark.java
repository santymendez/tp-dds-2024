package utils.map;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import lombok.Data;

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


  /**
   * Agrega un nuevo marcador con un lugar, latitud y longitud especificados.
   * Guarda la información del marcador en un archivo JSON.
   *
   * @param lugar el nombre del lugar para el marcador
   * @param lat la latitud del lugar
   * @param lng la longitud del lugar
   * @throws IOException si ocurre un error al guardar la información en un archivo
   */

  public void agregar(String lugar, Double lat, Double lng) throws IOException {
    // Se crea un objeto Map
    this.setLugar(lugar);
    this.setLat(lat);
    this.setLng(lng);
    File mapData = new File("src/main/resources/static/data.json");

    // Se crea un ObjectMapper
    ObjectMapper objectMapper = new ObjectMapper();

    // Se convierte a JSON
    String jsonData = objectMapper.writeValueAsString(this);

    // Se guarda el JSON en un archivo
    try (FileWriter fileWriter = new FileWriter(mapData)) {
      fileWriter.write(jsonData);
      System.out.println("JSON guardado correctamente en data.json");
    }
  }
}