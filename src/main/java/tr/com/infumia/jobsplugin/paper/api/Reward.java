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
 * an interface to determine rewards.
 */
public interface Reward extends TypeSerializer<Reward>, IdNameDescription {

  /**
   * the instance.
   */
  StringRegistry<Reward> REGISTRY = new StringRegistry<>() {
  };

  /**
   * gets the reward by id.
   *
   * @param id the id to get.
   *
   * @return reward.
   */
  @NotNull
  static Optional<Reward> get(@NotNull final String id) {
    return Reward.REGISTRY.get(id);
  }

  /**
   * gets the reward via id.
   *
   * @param id the id to get.
   *
   * @return reward.
   */
  @NotNull
  static Reward getOrThrow(@NotNull final String id) {
    return Reward.get(id).orElseThrow(() ->
      new IllegalStateException(String.format("Reward called %s not found!", id)));
  }

  /**
   * registers the given reward.
   *
   * @param reward the reward to register.
   */
  static void register(@NotNull final Reward reward) {
    Reward.REGISTRY.register(reward);
  }

  /**
   * gives the reward to the player.
   *
   * @param event the event to give.
   * @param employee the employee to give.
   * @param work the work to give.
   * @param mission the mission to give.
   */
  void reward(@NotNull Event event, @NotNull Employee employee, @NotNull Work work, @NotNull Mission mission);

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
    default void serialize(@NotNull final Reward reward, @NotNull final TransformedData transformedData) {
      reward.serialize(transformedData);
    }

    @Override
    default boolean supports(@NotNull final Class<?> cls) {
      return Reward.class.isAssignableFrom(cls);
    }
  }

  /**
   * an abstract implementation for {@link Reward}.
   */
  @Getter
  @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
  abstract class Base implements Reward {

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
     * the name.
     */
    @NotNull
    @Getter
    private final String name;
  }
}
