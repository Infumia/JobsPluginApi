package tr.com.infumia.jobsplugin.paper.api;

import org.jetbrains.annotations.NotNull;
import tr.com.infumia.infumialib.registry.StringKeyHolder;

/**
 * an interface to determine holder for id, name and description.
 */
public interface IdNameDescription extends StringKeyHolder {

  /**
   * obtains the description.
   *
   * @return description.
   */
  @NotNull
  String getDescription();

  /**
   * obtains the id.
   *
   * @return id.
   */
  @NotNull
  String getId();

  @NotNull
  @Override
  default String getKey() {
    return this.getId();
  }

  /**
   * obtains the name.
   *
   * @return name.
   */
  @NotNull
  String getName();
}
