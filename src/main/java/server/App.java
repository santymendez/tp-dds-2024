package server;

import cronjobs.CalculoHacerseCargo;
//import cronjobs.DetectorFallaDesconexion;
import cronjobs.GeneradorReporteCronJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Clase principal de la aplicación del servidor.
 */
public class App {

  public static void main(String[] args) {
    Server.init();
    startScheduler();
  }

  private static void startScheduler() {
    try {
      SchedulerFactory sf = new StdSchedulerFactory();
      Scheduler scheduler = sf.getScheduler();

      JobDetail jobCalculoHacerseCargo = JobBuilder.newJob(CalculoHacerseCargo.class)
          .withIdentity("jobCalculoHacerseCargo", "colaboraciones")
          .usingJobData("Info", "Valor")
          .build();

      Trigger triggerCalculoHacerseCargo = TriggerBuilder.newTrigger()
          .withIdentity("triggerCalculoHacerseCargo", "colaboraciones")
          .startNow()
          .withSchedule(CronScheduleBuilder.monthlyOnDayAndHourAndMinute(1, 0, 0))
          .build();

      JobDetail jobReportes = JobBuilder.newJob(GeneradorReporteCronJob.class)
          .withIdentity("jobReportes", "reportes")
          .usingJobData("Info", "Valor")
          .build();

      Trigger triggerReportes = TriggerBuilder.newTrigger()
          .withIdentity("triggerReportes", "reportes")
          .startNow()
          .withSchedule(CronScheduleBuilder.weeklyOnDayAndHourAndMinute(1, 0, 0))
          .build();
      /*
      JobDetail jobDetectorFallaConexion = JobBuilder.newJob(DetectorFallaDesconexion.class)
          .withIdentity("jobDetectorFallaConexion", "sensores")
          .usingJobData("Info", "Valor")
          .build();

      Trigger triggerDetectorFallaConexion = TriggerBuilder.newTrigger()
          .withIdentity("triggerDetectorFallaConexion", "sensores")
          .startNow()
          .withSchedule(
              CronScheduleBuilder.cronSchedule("0 0/5 * * * ?"))
          .build();
      */

      scheduler.scheduleJob(jobCalculoHacerseCargo, triggerCalculoHacerseCargo);
      scheduler.scheduleJob(jobReportes, triggerReportes);
      //scheduler.scheduleJob(jobDetectorFallaConexion, triggerDetectorFallaConexion);
      scheduler.start();

    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }
}