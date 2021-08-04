package tr.com.infumia.jobsplugin.paper.api.player;

import java.util.Optional;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.Callable;
import tr.com.infumia.jobsplugin.paper.api.event.EmployeeJobExpChangeEvent;
import tr.com.infumia.jobsplugin.paper.api.event.EmployeeJobLevelChangeEvent;
import tr.com.infumia.jobsplugin.paper.api.type.job.Job;
import tr.com.infumia.jobsplugin.paper.api.type.mission.Mission;

/**
 * an interface to determine works.
 */
public interface Work extends Callable {

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

  /**
   * sets the exp.
   *
   * @param affected the affected to set.
   * @param exp the exp to set.
   *
   * @return {@code true} if the exp successfully changes.
   */
  default boolean setExpWithEvent(@NotNull final Employee affected, @NotNull final Mission mission, final long exp) {
    return Callable.callEvent(new EmployeeJobExpChangeEvent(affected, this, mission, exp), event ->
      this.setExp(event.getExp()));
  }

  /**
   * sets the level.
   *
   * @param affected the affected to set.
   * @param level the level to set.
   *
   * @return {@code true} if the level successfully changes.
   */
  default boolean setLevelWithEvent(@NotNull final Employee affected, @NotNull final Mission mission,
                                    final long level) {
    return Callable.callEvent(new EmployeeJobLevelChangeEvent(affected, this, mission, level), event ->
      this.setLevel(event.getLevel()));
  }
}
