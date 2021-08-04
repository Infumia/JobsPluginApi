package tr.com.infumia.jobsplugin.paper.api.player;

import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.type.job.Job;

/**
 * an interface to determine works.
 */
public interface Work {

  /**
   * runs the job.
   *
   * @param event the event to run.
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
