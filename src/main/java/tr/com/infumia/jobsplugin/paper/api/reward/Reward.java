package tr.com.infumia.jobsplugin.paper.api.reward;

import org.jetbrains.annotations.NotNull;

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
}
