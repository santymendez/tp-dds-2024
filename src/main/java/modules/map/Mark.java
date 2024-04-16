package modules.map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Data
public class Mark {
    @JsonProperty("lugar")
    private String lugar;
    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lng")
    private Double lng;

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