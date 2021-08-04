package tr.com.infumia.jobsplugin.paper.api.reward;

import java.util.Optional;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.com.infumia.infumialib.transformer.ObjectSerializer;
import tr.com.infumia.infumialib.transformer.TransformedData;
import tr.com.infumia.infumialib.transformer.declarations.GenericDeclaration;
import tr.com.infumia.jobsplugin.paper.api.job.Job;
import tr.com.infumia.jobsplugin.paper.api.mission.Mission;
import tr.com.infumia.jobsplugin.paper.api.player.Employee;

/**
 * an interface to determine rewards.
 */
public interface Reward {

  /**
   * obtains the id.
   *
   * @return id.
   */
  @NotNull
  String getId();

  /**
   * gives the reward to the player.
   *
   * @param event the event to give.
   * @param employee the employee to give.
   * @param job the job to give.
   * @param mission the mission to give.
   */
  void reward(@NotNull Event event, @NotNull Employee employee, @NotNull Job job, @NotNull Mission mission);

  /**
   * an interface to determine serializers for {@link Reward}.
   */
  interface Serializer extends ObjectSerializer<Reward> {

    @NotNull
    @Override
    default Optional<Reward> deserialize(@NotNull final Reward field, @NotNull final TransformedData transformedData,
                                         @Nullable final GenericDeclaration declaration) {
      return this.deserialize(transformedData, declaration);
    }

    @Override
    default boolean supports(@NotNull final Class<?> cls) {
      return Reward.class.isAssignableFrom(cls);
    }
  }
}
