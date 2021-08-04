package tr.com.infumia.jobsplugin.paper.api.player;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine player jobs.
 */
public interface Employee {

  /**
   * gets employee or creates it.
   *
   * @param uniqueId the unique id to get.
   *
   * @return employee.
   */
  @NotNull
  static Employee getOrCreate(@NotNull final UUID uniqueId) {
    return Employees.getOrCreate(uniqueId);
  }

  /**
   * gets the employee if exist otherwise, creates and returns.
   *
   * @param uniqueId the unique id to get.
   *
   * @return employee.
   */
  @NotNull
  static CompletableFuture<@NotNull Employee> load(@NotNull final UUID uniqueId) {
    return Employees.load(uniqueId);
  }

  /**
   * runs all the works.
   *
   * @param event the event to run.
   */
  default void accept(@NotNull final Event event) {
    for (final var work : this.getWorks()) {
      work.accept(event, this);
    }
  }

  /**
   * adds the work.
   *
   * @param work the work to add.
   */
  void addWork(@NotNull Work work);

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
   * gets the work.
   *
   * @param id the id to get.
   *
   * @return work.
   */
  @NotNull
  Optional<Work> getWork(@NotNull String id);

  /**
   * obtains the works.
   *
   * @return works.
   */
  @NotNull
  Collection<Work> getWorks();

  /**
   * removes the work.
   *
   * @param id the id to remove.
   */
  void removeWork(@NotNull String id);
}
