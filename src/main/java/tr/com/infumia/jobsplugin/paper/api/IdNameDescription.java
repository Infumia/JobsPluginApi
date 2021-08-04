package tr.com.infumia.jobsplugin.paper.api;

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine holder for id, name and description.
 */
public interface IdNameDescription {

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

  /**
   * obtains the name.
   *
   * @return name.
   */
  @NotNull
  String getName();
}
