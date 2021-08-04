package tr.com.infumia.jobsplugin.paper.api.player;

import java.util.Objects;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tr.com.infumia.jobsplugin.paper.api.type.job.Job;

/**
 * an utility class that contains methods for {@link Job}.
 */
@UtilityClass
public class Works {

  /**
   * the work creator.
   */
  @Nullable
  private WorkCreator workCreator;

  /**
   * sets the work creator if it's not set already.
   *
   * @param workCreator the work creator to set.
   */
  public void setEmployeeCreator(@NotNull final WorkCreator workCreator) {
    if (Works.workCreator == null) {
      Works.workCreator = workCreator;
    }
  }

  /**
   * creates a new instance of work.
   *
   * @param employee the employee to create.
   * @param job the job to create.
   *
   * @return work.
   */
  @NotNull
  static Work get(@NotNull final Employee employee, @NotNull final Job job) {
    return Works.getWorkCreator().get(employee, job);
  }

  /**
   * gets the work creator.
   *
   * @return work creator.
   *
   * @throws NullPointerException if {@link #workCreator} is {@code null}.
   */
  @NotNull
  private WorkCreator getWorkCreator() {
    return Objects.requireNonNull(Works.workCreator, "work creator");
  }
}
