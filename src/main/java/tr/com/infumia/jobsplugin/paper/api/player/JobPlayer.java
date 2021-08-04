package tr.com.infumia.jobsplugin.paper.api.player;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.type.Job;

/**
 * an interface to determine player jobs.
 */
public interface JobPlayer {

  /**
   * gets the job.
   *
   * @param id the id to get.
   *
   * @return jobs.
   */
  @NotNull
  Optional<Job> getJob(@NotNull String id);

  /**
   * obtains the jobs.
   *
   * @return jobs.
   */
  @NotNull
  Collection<Job> getJobs();

  /**
   * obtains the offline player.
   *
   * @return player as offline player.
   */
  @NotNull
  default OfflinePlayer getOfflinePlayer() {
    return Bukkit.getOfflinePlayer(this.getPlayerUniqueId());
  }

  /**
   * obtains the player.
   *
   * @return player.
   */
  @NotNull
  default Optional<Player> getPlayer() {
    return Optional.ofNullable(Bukkit.getPlayer(this.getPlayerUniqueId()));
  }

  /**
   * obtains the player unique id.
   *
   * @return player's unique id.
   */
  @NotNull
  UUID getPlayerUniqueId();

  /**
   * removes the job.
   *
   * @param id the id to remove.
   */
  void removeJob(@NotNull String id);
}
