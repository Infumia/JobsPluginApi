package tr.com.infumia.jobsplugin.paper.api.condition;

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine conditions.
 */
public interface Condition {

  /**
   * obtains the id.
   *
   * @return id.
   */
  @NotNull
  String getId();
}
