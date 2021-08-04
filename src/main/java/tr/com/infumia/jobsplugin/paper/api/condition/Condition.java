package tr.com.infumia.jobsplugin.paper.api.condition;

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
 * an interface to determine conditions.
 */
public interface Condition {

  /**
   * tests the condition.
   *
   * @param event the event to test.
   * @param employee the employee to test.
   * @param job the job to test.
   * @param mission the mission to test.
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

  /**
   * serializes condition.
   *
   * @param data the data to serialize.
   */
  void serialize(@NotNull TransformedData data);

  /**
   * an interface to determine serializers for {@link Condition}.
   */
  interface Serializer extends ObjectSerializer<Condition> {

    @NotNull
    @Override
    default Optional<Condition> deserialize(@NotNull final Condition field,
                                            @NotNull final TransformedData transformedData,
                                            @Nullable final GenericDeclaration declaration) {
      return this.deserialize(transformedData, declaration);
    }

    @Override
    default void serialize(@NotNull final Condition condition, @NotNull final TransformedData transformedData) {
      condition.serialize(transformedData);
    }

    @Override
    default boolean supports(@NotNull final Class<?> cls) {
      return Condition.class.isAssignableFrom(cls);
    }
  }
}
