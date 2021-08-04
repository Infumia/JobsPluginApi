package tr.com.infumia.jobsplugin.paper.api.mission;

import java.util.Collection;
import java.util.Optional;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.infumialib.transformer.ObjectSerializer;
import tr.com.infumia.jobsplugin.paper.api.condition.Condition;
import tr.com.infumia.jobsplugin.paper.api.job.Job;
import tr.com.infumia.jobsplugin.paper.api.player.Employee;
import tr.com.infumia.jobsplugin.paper.api.reward.Reward;

/**
 * an interface to determine missions.
 */
public interface Mission {

  /**
   * runs all the conditions and rewards.
   *
   * @param job the job to run.
   * @param employee the employee to run.
   * @param event the event to run.
   */
  default void accept(@NotNull final Event event, @NotNull final Employee employee, @NotNull final Job job) {
    for (final var condition : this.getConditions()) {
      if (!condition.condition(event, employee, job, this)) {
        return;
      }
    }
    for (final var reward : this.getRewards()) {
      reward.reward(event, employee, job, this);
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
   * obtains the id.
   *
   * @return id.
   */
  @NotNull
  String getId();

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

  }
}
