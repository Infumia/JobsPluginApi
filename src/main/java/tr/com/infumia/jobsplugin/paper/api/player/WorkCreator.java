package tr.com.infumia.jobsplugin.paper.api.player;

import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.type.job.Job;

/**
 * an interface to determine work creator.
 */
public interface WorkCreator {

  /**
   * creates a new instance of work.
   *
   * @param employee the employee to create.
   * @param job the job to create.
   *
   * @return work.
   */
  @NotNull
  Work get(@NotNull Employee employee, @NotNull Job job);
}
