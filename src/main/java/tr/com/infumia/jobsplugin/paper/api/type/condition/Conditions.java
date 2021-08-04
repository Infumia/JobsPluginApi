package tr.com.infumia.jobsplugin.paper.api.type.condition;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

/**
 * an utility class that contains methods for {@link Condition}.
 */
@UtilityClass
class Conditions {

  /**
   * the conditions.
   */
  private final Map<String, Condition> CONDITIONS = new ConcurrentHashMap<>();

  /**
   * gets the condition via id.
   *
   * @param id the id to get.
   *
   * @return condition.
   */
  @NotNull
  static Optional<Condition> get(@NotNull final String id) {
    return Optional.ofNullable(Conditions.CONDITIONS.get(id));
  }

  /**
   * registers the condition.
   *
   * @param condition the condition to register.
   */
  static void register(@NotNull final Condition condition) {
    Conditions.CONDITIONS.put(condition.getId(), condition);
  }
}
