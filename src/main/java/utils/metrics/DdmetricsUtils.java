package utils.metrics;

import config.Config;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmHeapPressureMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.system.FileDescriptorMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.step.StepMeterRegistry;
import io.micrometer.datadog.DatadogConfig;
import io.micrometer.datadog.DatadogMeterRegistry;
import java.time.Duration;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * Clase que encapsula la inicialización de las métricas de la aplicación.
 */

@Getter
@Slf4j
public class DdmetricsUtils {
  private final StepMeterRegistry registry;

  /**
   * Constructor de la clase DdmetricsUtils.
   *
   * @param appTag tag.
   */

  public DdmetricsUtils(String appTag) {
    // crea un registro para nuestras métricas basadas en DD
    var config = new DatadogConfig() {
      @Override
      public Duration step() {
        return Duration.ofSeconds(10);
      }

      @Override
      @NotNull
      public String apiKey() {
        return Config.getDataDogApiKey();
      }

      @Override
      public String uri() {
        return "https://api.us5.datadoghq.com";
      }

      @Override
      public String get(String k) {
        return null; // accept the rest of the defaults
      }
    };
    registry = new DatadogMeterRegistry(config, Clock.SYSTEM);
    registry.config().commonTags("app", appTag);
    initInfraMonitoring();
  }

  private void initInfraMonitoring() {
    try (var jvmGcMetrics = new JvmGcMetrics();
         var jvmHeapPressureMetrics = new JvmHeapPressureMetrics()) {
      jvmGcMetrics.bindTo(registry);
      jvmHeapPressureMetrics.bindTo(registry);
    }
    new JvmMemoryMetrics().bindTo(registry);
    new ProcessorMetrics().bindTo(registry);
    new FileDescriptorMetrics().bindTo(registry);
  }
}