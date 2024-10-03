package server;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;
import java.io.IOException;
import java.util.function.Consumer;
import io.javalin.http.staticfiles.Location;
import utils.javalin.Initializer;
import utils.javalin.JavalinRenderer;
import utils.sender.Config;

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

      int port = Integer
          .parseInt(Config.getServerPort());
      app = Javalin.create(config()).start(port);

      Router.init(app);

      if (Boolean.parseBoolean(Config.getDevMode())) {
        Initializer.init("simple-persistence-unit");
      } else {
        Initializer.init("database-persistence-unit");
      }
    }
  }

  private static Consumer<JavalinConfig> config() {
    return config -> {
      config.staticFiles.add(staticFiles -> {
        staticFiles.hostedPath = "/";
        staticFiles.directory = "public";
        staticFiles.location = Location.CLASSPATH;
      });

      config.staticFiles.add(staticFiles -> {
        staticFiles.hostedPath = "/uploaded-imgs";
        staticFiles.directory = "uploaded-imgs";
        staticFiles.location = Location.EXTERNAL;
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
