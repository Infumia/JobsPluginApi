package tr.com.infumia.jobsplugin.paper.api.job;

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine jobs.
 */
public interface Job {

  /**
   * obtains the id.
   *
   * @return id.
   */
  @NotNull
  String getId();
}
