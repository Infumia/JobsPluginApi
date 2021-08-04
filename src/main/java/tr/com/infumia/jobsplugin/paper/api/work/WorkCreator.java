package tr.com.infumia.jobsplugin.paper.api.work;

import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.job.Job;

/**
 * an interface to determine work creator.
 */
public interface WorkCreator {

  /**
   * creates a new instance of work.
   *
   * @param job the job to create.
   *
   * @return work.
   */
  @NotNull
  Work get(@NotNull Job job);
}
