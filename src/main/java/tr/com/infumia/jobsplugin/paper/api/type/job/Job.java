package tr.com.infumia.jobsplugin.paper.api.type.job;

import java.util.Collection;
import java.util.Optional;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.com.infumia.infumialib.transformer.ObjectSerializer;
import tr.com.infumia.infumialib.transformer.TransformedData;
import tr.com.infumia.infumialib.transformer.declarations.GenericDeclaration;
import tr.com.infumia.jobsplugin.paper.api.player.Employee;
import tr.com.infumia.jobsplugin.paper.api.type.TypeSerializer;
import tr.com.infumia.jobsplugin.paper.api.type.mission.Mission;

/**
 * an interface to determine jobs.
 */
public interface Job extends TypeSerializer<Job> {

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

  /**
   * an interface to determine serializers for {@link Job}.
   */
  interface Serializer extends ObjectSerializer<Job> {

    @NotNull
    @Override
    default Optional<Job> deserialize(@NotNull final Job field, @NotNull final TransformedData transformedData,
                                      @Nullable final GenericDeclaration declaration) {
      return this.deserialize(transformedData, declaration);
    }

    @Override
    default void serialize(@NotNull final Job job, @NotNull final TransformedData transformedData) {
      job.serialize(transformedData);
    }

    @Override
    default boolean supports(@NotNull final Class<?> cls) {
      return Job.class.isAssignableFrom(cls);
    }
  }
}
