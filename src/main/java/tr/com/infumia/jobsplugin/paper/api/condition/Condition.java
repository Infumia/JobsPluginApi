package tr.com.infumia.jobsplugin.paper.api.condition;

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
 * an interface to determine conditions.
 */
public interface Condition extends TypeSerializer<Condition>, IdNameDescription {

  /**
   * tests the condition.
   *
   * @param event the event to test.
   * @param employee the employee to test.
   * @param work the work to test.
   * @param mission the mission to test.
   *
   * @return {@code true} if the event passes the condition.
   */
  boolean condition(@NotNull Event event, @NotNull Employee employee, @NotNull Work work, @NotNull Mission mission);

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

  /**
   * an abstract implementation for {@link Condition}.
   */
  @Getter
  @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
  abstract class Base implements Condition {

    /**
     * the description.
     */
    @NotNull
    private final String description;

    /**
     * the id.
     */
    @NotNull
    private final String id;

    /**
     * the name.
     */
    @NotNull
    private final String name;
  }
}
