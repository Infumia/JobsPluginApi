package tr.com.infumia.jobsplugin.paper.api.job;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.mission.Mission;

/**
 * an interface to determine jobs.
 */
public interface Job {

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
   * removes the mission.
   *
   * @param id the id to remove.
   */
  void removeMission(@NotNull String id);
}
