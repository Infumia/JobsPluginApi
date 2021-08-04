package tr.com.infumia.jobsplugin.paper.api.mission;

import java.util.Collection;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.condition.Condition;
import tr.com.infumia.jobsplugin.paper.api.reward.Reward;

/**
 * an interface to determine missions.
 */
public interface Mission {

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
  Collection<Reward> getConditions();

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
}
