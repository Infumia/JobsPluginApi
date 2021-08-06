package tr.com.infumia.jobsplugin.paper.api.mission;

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
import tr.com.infumia.jobsplugin.paper.api.condition.Condition;
import tr.com.infumia.jobsplugin.paper.api.employee.Employee;
import tr.com.infumia.jobsplugin.paper.api.employee.Work;
import tr.com.infumia.jobsplugin.paper.api.reward.Reward;

/**
 * an interface to determine missions.
 */
public interface Mission extends TypeSerializer<Mission>, IdNameDescription {

  /**
   * gets the mission by id.
   *
   * @param id the id to get.
   *
   * @return mission.
   */
  @NotNull
  static Optional<Mission> get(@NotNull final String id) {
    return Missions.get(id);
  }

  /**
   * registers the given mission.
   *
   * @param mission the mission to register.
   */
  static void register(@NotNull final Mission mission) {
    Missions.register(mission);
  }

  /**
   * runs all the conditions and rewards.
   *
   * @param event the event to run.
   * @param employee the employee to run.
   * @param work the work to run.
   */
  default void accept(@NotNull final Event event, @NotNull final Employee employee, @NotNull final Work work) {
    for (final var condition : this.getConditions()) {
      if (!condition.condition(event, employee, work, this)) {
        return;
      }
    }
    for (final var reward : this.getRewards()) {
      reward.reward(event, employee, work, this);
    }
  }

  /**
   * adds the condition.
   *
   * @param condition the condition to add.
   */
  void addCondition(@NotNull Condition condition);

  /**
   * adds the reward.
   *
   * @param reward the reward to add.
   */
  void addReward(@NotNull Reward reward);

  /**
   * gets the condition.
   *
   * @param id the id to get.
   *
   * @return condition.
   */
  @NotNull
  Optional<Condition> getCondition(@NotNull String id);

  /**
   * obtains the conditions.
   *
   * @return conditions.
   */
  @NotNull
  Collection<Condition> getConditions();

  /**
   * gets the reward.
   *
   * @param id the id to get.
   *
   * @return reward.
   */
  @NotNull
  Optional<Reward> getReward(@NotNull String id);

  /**
   * obtains the rewards.
   *
   * @return rewards.
   */
  @NotNull
  Collection<Reward> getRewards();

  /**
   * removes the condition.
   *
   * @param id the id to remove.
   */
  void removeCondition(@NotNull String id);

  /**
   * removes the reward.
   *
   * @param id the id to remove.
   */
  void removeReward(@NotNull String id);

  /**
   * an interface to determine serializers for {@link Mission}.
   */
  interface Serializer extends ObjectSerializer<Mission> {

    @NotNull
    @Override
    default Optional<Mission> deserialize(@NotNull final Mission field, @NotNull final TransformedData transformedData,
                                          @Nullable final GenericDeclaration declaration) {
      return this.deserialize(transformedData, declaration);
    }

    @Override
    default void serialize(@NotNull final Mission mission, @NotNull final TransformedData transformedData) {
      mission.serialize(transformedData);
    }

    @Override
    default boolean supports(@NotNull final Class<?> cls) {
      return Mission.class.isAssignableFrom(cls);
    }
  }

  /**
   * an abstract implementation for {@link Mission}.
   */
  @Getter
  @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
  abstract class Base implements Mission {

    /**
     * the conditions.
     */
    @NotNull
    private final Map<String, Condition> conditions;

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

    /**
     * the rewards.
     */
    @NotNull
    private final Map<String, Reward> rewards;

    @Override
    public final void addCondition(@NotNull final Condition condition) {
      this.conditions.put(condition.getId(), condition);
    }

    @Override
    public final void addReward(@NotNull final Reward reward) {
      this.rewards.put(reward.getId(), reward);
    }

    @NotNull
    @Override
    public final Optional<Condition> getCondition(@NotNull final String id) {
      return Optional.ofNullable(this.conditions.get(id));
    }

    @NotNull
    @Override
    public final Collection<Condition> getConditions() {
      return Collections.unmodifiableCollection(this.conditions.values());
    }

    @NotNull
    @Override
    public final Optional<Reward> getReward(@NotNull final String id) {
      return Optional.ofNullable(this.rewards.get(id));
    }

    @NotNull
    @Override
    public final Collection<Reward> getRewards() {
      return Collections.unmodifiableCollection(this.rewards.values());
    }

    @Override
    public final void removeCondition(@NotNull final String id) {
      this.conditions.remove(id);
    }

    @Override
    public final void removeReward(@NotNull final String id) {
      this.rewards.remove(id);
    }
  }
}
