package tr.com.infumia.jobsplugin.paper.api.job;

import java.util.Collection;
import java.util.Optional;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.mission.Mission;
import tr.com.infumia.jobsplugin.paper.api.player.Employee;

/**
 * an interface to determine jobs.
 */
public interface Job {

  /**
   * runs all the missions.
   *
   * @param event the event to run.
   * @param employee the employee to run.
   */
  default void accept(@NotNull final Event event, @NotNull final Employee employee) {
    for (final var mission : this.getMissions()) {
      mission.accept(event, employee, this);
    }
  }

  /**
   * adds the mission.
   *
   * @param mission the mission to add.
   */
  void addMission(@NotNull Mission mission);

  /**
   * obtains the id.
   *
   * @return id.
   */
  @NotNull
  String getId();

  /**
   * gets the mission.
   *
   * @param id the id to get.
   *
   * @return condition.
   */
  @NotNull
  Optional<Mission> getMission(@NotNull String id);

  /**
   * obtains the missions.
   *
   * @return missions.
   */
  @NotNull
  Collection<Mission> getMissions();

  /**
   * removes the mission.
   *
   * @param id the id to remove.
   */
  void removeMission(@NotNull String id);
}
