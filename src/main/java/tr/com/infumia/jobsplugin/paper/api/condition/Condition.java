package tr.com.infumia.jobsplugin.paper.api.condition;

import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.job.Job;
import tr.com.infumia.jobsplugin.paper.api.mission.Mission;
import tr.com.infumia.jobsplugin.paper.api.player.Employee;

/**
 * an interface to determine conditions.
 */
public interface Condition {

  /**
   * tests the condition.
   *
   * @param employee the employee to test.
   * @param mission the mission to test.
   * @param event the event to test.
   *
   * @return {@code true} if the event passes the condition.
   */
  boolean condition(@NotNull Event event, @NotNull Employee employee, @NotNull Job job, @NotNull Mission mission);

  /**
   * obtains the id.
   *
   * @return id.
   */
  @NotNull
  String getId();
}
