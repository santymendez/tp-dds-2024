package server;

import brokers.sensores.movimiento.BrokerSensorMovimiento;
import brokers.sensores.temperatura.BrokerSensorTemperatura;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import config.Config;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;
import io.javalin.http.staticfiles.Location;
import java.io.IOException;
import java.util.function.Consumer;
import middlewares.AuthMiddleware;
import server.handlers.AppHandlers;
import utils.javalin.Initializer;
import utils.javalin.JavalinRenderer;

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

      AuthMiddleware.apply(app);
      AppHandlers.applyHandlers(app);
      Router.init(app);

      if (Boolean.parseBoolean(Config.getDevMode())) {
        Initializer.init("simple-persistence-unit");
      } else {
        Initializer.init("database-persistence-unit");
      }

      BrokerSensorTemperatura brokerSensorTemperatura = new BrokerSensorTemperatura();
      brokerSensorTemperatura.suscribir(Config.getTempTopic(), Config.getTempCli());

      BrokerSensorMovimiento brokerSensorMovimiento = new BrokerSensorMovimiento();
      brokerSensorMovimiento.suscribir(Config.getMovTopic(), Config.getMovCli());
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

      config.staticFiles.add(staticFiles -> {
        staticFiles.hostedPath = "/reportes";
        staticFiles.directory = "reportes";
        staticFiles.location = Location.EXTERNAL;
      });

      config.fileRenderer(new JavalinRenderer().register("hbs", (path, model, context) -> {
        Handlebars handlebars = new Handlebars();

        handlebars.registerHelper("verifyRole", (role, options) -> {
          String expectedRole = options.param(0);
          if (role != null && role.toString().equals(expectedRole)) {
            return options.fn();
          } else {
            return options.inverse();
          }
        });

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
