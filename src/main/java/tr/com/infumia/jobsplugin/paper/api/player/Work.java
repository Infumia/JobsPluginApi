package tr.com.infumia.jobsplugin.paper.api.player;

import java.util.Optional;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.type.job.Job;

/**
 * an interface to determine works.
 */
public interface Work {

  /**
   * creates a new instance of work.
   *
   * @param id the id to create.
   *
   * @return work.
   */
  @NotNull
  static Optional<Work> get(@NotNull final String id) {
    return Job.get(id)
      .map(Work::get);
  }

  /**
   * creates a new instance of work.
   *
   * @param job the job to create.
   *
   * @return work.
   */
  @NotNull
  static Work get(@NotNull final Job job) {
    return Works.get(job);
  }

  /**
   * runs the job.
   *
   * @param event the event to run.
   * @param employee the employee.
   */
  default void accept(@NotNull final Event event, @NotNull final Employee employee) {
    this.getJob().accept(event, employee, this);
  }

  /**
   * obtains the exp.
   *
   * @return exp.
   */
  long getExp();

  /**
   * sets the exp.
   *
   * @param exp the exp to set.
   */
  void setExp(long exp);

  /**
   * obtains the job.
   *
   * @return job.
   */
  @NotNull
  Job getJob();

  /**
   * obtains the level.
   *
   * @return level.
   */
  long getLevel();

  /**
   * sets the level.
   *
   * @param level the level to set.
   */
  void setLevel(long level);
}
