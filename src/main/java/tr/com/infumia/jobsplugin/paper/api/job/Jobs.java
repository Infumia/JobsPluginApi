package tr.com.infumia.jobsplugin.paper.api.job;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

/**
 * an utility class that contains methods for {@link Job}.
 */
@UtilityClass
class Jobs {

  /**
   * the jobs.
   */
  private final Map<String, Job> JOBS = new ConcurrentHashMap<>();

  /**
   * gets the job via id.
   *
   * @param id the id to get.
   *
   * @return job.
   */
  @NotNull
  static Optional<Job> get(@NotNull final String id) {
    return Optional.ofNullable(Jobs.JOBS.get(id));
  }

  /**
   * registers the job.
   *
   * @param job the job to register.
   */
  static void register(@NotNull final Job job) {
    Jobs.JOBS.put(job.getId(), job);
  }
}
