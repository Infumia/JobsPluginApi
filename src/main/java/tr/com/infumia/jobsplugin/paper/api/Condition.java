package tr.com.infumia.jobsplugin.paper.api;

import java.util.Optional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.com.infumia.infumialib.registry.StringRegistry;
import tr.com.infumia.infumialib.transformer.ObjectSerializer;
import tr.com.infumia.infumialib.transformer.TransformedData;
import tr.com.infumia.infumialib.transformer.declarations.GenericDeclaration;
import tr.com.infumia.jobsplugin.paper.api.employee.Employee;
import tr.com.infumia.jobsplugin.paper.api.employee.Work;

/**
 * an interface to determine conditions.
 */
public interface Condition extends TypeSerializer<Condition>, IdNameDescription {

  /**
   * the instance.
   */
  StringRegistry<Condition> REGISTRY = new StringRegistry<>() {
  };

  /**
   * gets the condition via id.
   *
   * @param id the id to get.
   *
   * @return condition.
   */
  @NotNull
  static Optional<Condition> get(@NotNull final String id) {
    return Condition.REGISTRY.get(id);
  }

  /**
   * gets the condition via id.
   *
   * @param id the id to get.
   *
   * @return condition.
   */
  @NotNull
  static Condition getOrThrow(@NotNull final String id) {
    return Condition.get(id).orElseThrow(() ->
      new IllegalStateException(String.format("Condition called %s not found!", id)));
  }

  /**
   * registers the condition.
   *
   * @param condition the condition to register.
   */
  static void register(@NotNull final Condition condition) {
    Condition.REGISTRY.register(condition);
  }

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
