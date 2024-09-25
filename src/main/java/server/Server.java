package server;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;
import java.io.IOException;
import java.util.function.Consumer;
import utils.javalin.Initializer;
import utils.javalin.JavalinRenderer;
import utils.javalin.PrettyProperties;

/**
 * Clase que inicializa el servidor Javalin y configura las rutas de la aplicación.
 */

public class Server {
  private static Javalin app = null;

  /**
   * Devuelve la instancia de la aplicación Javalin.
   *
   * @return Javalin - Instancia de la aplicación Javalin.
   * @throws RuntimeException - Si la aplicación no ha sido inicializada.
   */

  public static Javalin app() {

    if (app == null) {
      throw new RuntimeException("App no inicializada");
    }

    return app;
  }

  /**
   * Inicializa la aplicación Javalin y configura las rutas de la aplicación.
   */

  public static void init() {
    if (app == null) {

      Integer port = Integer
          .parseInt(PrettyProperties.getInstance().propertyFromName("server_port"));
      app = Javalin.create(config()).start(port);

      Router.init(app);

      if (Boolean.parseBoolean(PrettyProperties.getInstance().propertyFromName("dev_mode"))) {
        Initializer.init();
      }
    }
  }

  private static Consumer<JavalinConfig> config() {
    return config -> {
      config.staticFiles.add(staticFiles -> {
        staticFiles.hostedPath = "/";
        staticFiles.directory = "/public";
      });

      config.fileRenderer(new JavalinRenderer().register("hbs", (path, model, context) -> {
        Handlebars handlebars = new Handlebars();
        Template template = null;
        try {
          template = handlebars.compile(
              "templates/" + path.replace(".hbs", ""));
          return template.apply(model);
        } catch (IOException e) {
          e.printStackTrace();
          context.status(HttpStatus.NOT_FOUND);
          return "No se encuentra la página indicada...";
        }
      }));
    };
  }
}
