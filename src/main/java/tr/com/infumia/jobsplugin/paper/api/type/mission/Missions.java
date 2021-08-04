package tr.com.infumia.jobsplugin.paper.api.type.mission;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

/**
 * an utility class that contains methods for {@link Mission}.
 */
@UtilityClass
class Missions {

  /**
   * the missions.
   */
  private final Map<String, Mission> MISSIONS = new ConcurrentHashMap<>();

  /**
   * gets the mission via id.
   *
   * @param id the id to get.
   *
   * @return mission.
   */
  @NotNull
  static Optional<Mission> get(@NotNull final String id) {
    return Optional.ofNullable(Missions.MISSIONS.get(id));
  }

  /**
   * registers the mission.
   *
   * @param mission the mission to register.
   */
  static void register(@NotNull final Mission mission) {
    Missions.MISSIONS.put(mission.getId(), mission);
  }
}
