package tr.com.infumia.jobsplugin.paper.api.job;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.com.infumia.infumialib.transformer.ObjectSerializer;
import tr.com.infumia.infumialib.transformer.TransformedData;
import tr.com.infumia.infumialib.transformer.declarations.GenericDeclaration;
import tr.com.infumia.jobsplugin.paper.api.IdNameDescription;
import tr.com.infumia.jobsplugin.paper.api.TypeSerializer;
import tr.com.infumia.jobsplugin.paper.api.employee.Employee;
import tr.com.infumia.jobsplugin.paper.api.employee.Work;
import tr.com.infumia.jobsplugin.paper.api.mission.Mission;

/**
 * an interface to determine jobs.
 */
public interface Job extends TypeSerializer<Job>, IdNameDescription {

  /**
   * gets the job via id.
   *
   * @param id the id to get.
   *
   * @return job.
   */
  @NotNull
  static Optional<Job> get(@NotNull final String id) {
    return Jobs.get(id);
  }

  /**
   * gets the job via id.
   *
   * @param id the id to get.
   *
   * @return job.
   */
  @NotNull
  static Job getOrThrow(@NotNull final String id) {
    return Jobs.get(id).orElseThrow(() ->
      new IllegalStateException(String.format("Job called %s not found!", id)));
  }

  /**
   * registers the job.
   *
   * @param job the job to register.
   */
  static void register(@NotNull final Job job) {
    Jobs.register(job);
  }

  /**
   * runs all the missions.
   *
   * @param event the event to run.
   * @param employee the employee to run.
   * @param work the work to run.
   */
  default void accept(@NotNull final Event event, @NotNull final Employee employee, @NotNull final Work work) {
    for (final var mission : this.getMissions()) {
      mission.accept(event, employee, work);
    }
  }

  /**
   * adds the mission.
   *
   * @param mission the mission to add.
   */
  void addMission(@NotNull Mission mission);

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

  /**
   * an abstract implementation for {@link Job}.
   */
  @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
  abstract class Base implements Job {

    /**
     * the description.
     */
    @NotNull
    @Getter
    private final String description;

    /**
     * the id.
     */
    @NotNull
    @Getter
    private final String id;

    /**
     * the missions.
     */
    @NotNull
    private final Map<String, Mission> missions;

    /**
     * the name.
     */
    @NotNull
    @Getter
    private final String name;

    @Override
    public final void addMission(@NotNull final Mission mission) {
      this.missions.put(mission.getId(), mission);
    }

    @NotNull
    @Override
    public final Optional<Mission> getMission(@NotNull final String id) {
      return Optional.ofNullable(this.missions.get(id));
    }

    @NotNull
    @Override
    public final Collection<Mission> getMissions() {
      return Collections.unmodifiableCollection(this.missions.values());
    }

    @Override
    public final void removeMission(@NotNull final String id) {
      this.missions.remove(id);
    }
  }
}
