package tr.com.infumia.jobsplugin.paper.api.type.reward;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

/**
 * an utility class that contains methods for {@link Reward}.
 */
@UtilityClass
class Rewards {

  /**
   * the rewards.
   */
  private final Map<String, Reward> REWARDS = new ConcurrentHashMap<>();

  /**
   * gets the reward via id.
   *
   * @param id the id to get.
   *
   * @return reward.
   */
  @NotNull
  static Optional<Reward> get(@NotNull final String id) {
    return Optional.ofNullable(Rewards.REWARDS.get(id));
  }

  /**
   * registers the reward.
   *
   * @param reward the reward to register.
   */
  static void register(@NotNull final Reward reward) {
    Rewards.REWARDS.put(reward.getId(), reward);
  }
}
