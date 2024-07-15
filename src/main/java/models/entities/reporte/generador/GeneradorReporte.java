package models.entities.reporte.generador;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import models.entities.heladera.Heladera;
import models.entities.reporte.ViandasPorColaborador;
import models.repositories.heladera.HeladerasRepository;
import models.repositories.heladera.InterfaceHeladerasRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

/**
 * Clase para generar los reportes semanales.
 */

@Setter
@Getter
public class GeneradorReporte {

  private static final Integer startX = 50;
  private static final Integer startY = 780;
  private static final Integer leading = 20;
  private static final Integer maxLines = 34;
  private static Integer currentLines;
  private static InterfaceHeladerasRepository heladerasRepository;

  public GeneradorReporte(InterfaceHeladerasRepository heladerasRepository) {
    GeneradorReporte.heladerasRepository = heladerasRepository;
  }

  /**
   * Genera un reporte semanal.
   */

  public static void main(String[] args) {

    LocalDate semanaActual = LocalDate.now();

    String path = "reportes/reporte-semana-" + semanaActual + ".pdf";

    try (PDDocument document = new PDDocument()) {
      List<Heladera> heladeras = heladerasRepository.obtenerHeladeras();

      for (Heladera heladera : heladeras) {
        String nombreHeladera = heladera.getNombre();
        int cantFallas = heladera.getModReportes().getReporteHeladera().getFallas();
        int cantViandasColocadas = heladera.getModReportes().getReporteHeladera().getViandasColocadas();
        int cantViandasRetiradas = heladera.getModReportes().getReporteHeladera().getViandasRetiradas();
        List<ViandasPorColaborador> viandasPorColaboradores =
            heladera.getModReportes().getReporteHeladera().getViandasPorColaboradores();

        generarReporte(document, "Reporte Heladera: " + nombreHeladera,
            cantFallas, cantViandasColocadas, cantViandasRetiradas, viandasPorColaboradores, path);
        document.save(path);
      }

      System.out.println("Se generó el PDF correctamente en: " + path);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void generarReporte(
      PDDocument document, String nombreHeladera,
      int cantFallas, int cantViandasColocadas,
      int cantViandasRetiradas,
      List<ViandasPorColaborador> viandasPorColaboradores,
      String path
  ) throws IOException {

    PDPage page = new PDPage(PDRectangle.A4);
    document.addPage(page);
    PDPageContentStream contentStream = new PDPageContentStream(document, page);

    PDFont pdfFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
    contentStream.setFont(pdfFont, 14);

    int y = startY;
    currentLines = maxLines;
    y = agregarLineaTexto(contentStream, y, nombreHeladera);
    y = agregarLineaTexto(
        contentStream, y, "Cantidad de Fallas: " + cantFallas);
    y = agregarLineaTexto(
        contentStream, y, "Cantidad de Viandas Colocadas: " + cantViandasColocadas);
    y = agregarLineaTexto(
        contentStream, y, "Cantidad de Viandas Retiradas: " + cantViandasRetiradas);


    for (ViandasPorColaborador viandasPorColaborador : viandasPorColaboradores) {
      //Si me paso de líneas, creo una nueva página para la misma heladera.
      if (currentLines == 0) {
        contentStream.close();
        document.save(path);
        PDPage newPage = new PDPage(PDRectangle.A4);
        document.addPage(newPage);
        contentStream = new PDPageContentStream(document, newPage);
        contentStream.setFont(pdfFont, 14);
        y = startY;
        currentLines = 34;
      }
      String nombreColaborador = viandasPorColaborador.getColaborador().getNombre();
      int cantViandas = viandasPorColaborador.getViandas();
      y = agregarLineaTexto(
          contentStream, y, "Colaborador: " + nombreColaborador + " - Viandas: " + cantViandas);
    }
    contentStream.close();
  }

  private static int agregarLineaTexto(
      PDPageContentStream contentStream,
      int y, String texto
  ) throws IOException {
    contentStream.beginText();
    contentStream.newLineAtOffset(startX, y);
    contentStream.showText(texto);
    contentStream.endText();
    currentLines--;
    return y - leading;
  }
}