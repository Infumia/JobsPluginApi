package tr.com.infumia.jobsplugin.paper.api.player;

import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.job.Job;

/**
 * an interface to determine works.
 */
public interface Work {

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
