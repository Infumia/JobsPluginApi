package tr.com.infumia.jobsplugin.paper.api.employee;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.Callable;
import tr.com.infumia.jobsplugin.paper.api.Job;
import tr.com.infumia.jobsplugin.paper.api.event.EmployeeCloseEvent;
import tr.com.infumia.jobsplugin.paper.api.event.EmployeeJoinJobEvent;
import tr.com.infumia.jobsplugin.paper.api.event.EmployeeLoadEvent;
import tr.com.infumia.jobsplugin.paper.api.event.EmployeeQuitJobEvent;

/**
 * an interface to determine player jobs.
 */
public interface Employee extends Callable {

  /**
   * closes the employee, runs when the player quits from the server.
   *
   * @param employee the employee to close.
   *
   * @return completed future.
   */
  @NotNull
  static CompletableFuture<Void> close(@NotNull final Employee employee) {
    return Employees.close(employee).whenCompleteAsync((unused, throwable) ->
      new EmployeeCloseEvent(employee).callEvent());
  }

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
    return Employees.load(uniqueId).thenApply(employee -> {
      final var employeeLoadEvent = new EmployeeLoadEvent(employee);
      employeeLoadEvent.callEvent();
      return employeeLoadEvent.getEmployee();
    });
  }

  /**
   * saves the employee.
   *
   * @param employee the employee to save.
   *
   * @return completed future.
   */
  @NotNull
  static CompletableFuture<Void> save(@NotNull final Employee employee) {
    return Employees.save(employee);
  }

  /**
   * saves all the employees.
   *
   * @return completed future.
   */
  @NotNull
  static CompletableFuture<Void> saveAll() {
    return Employees.saveAll();
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
   * adds the work.
   *
   * @param id the id to add.
   *
   * @return {@code true} if player successfully join to the work.
   */
  default boolean addWorkWithEvent(@NotNull final String id) {
    return Job.get(id)
      .map(this::addWorkWithEvent)
      .orElse(false);
  }

  /**
   * adds the work.
   *
   * @param job the job to add.
   *
   * @return {@code true} if player successfully join to the work.
   */
  default boolean addWorkWithEvent(@NotNull final Job job) {
    return this.addWorkWithEvent(Work.of(job));
  }

  /**
   * adds the work.
   *
   * @param work the work to add.
   *
   * @return {@code true} if player successfully join to the work.
   */
  default boolean addWorkWithEvent(@NotNull final Work work) {
    return Callable.callEvent(new EmployeeJoinJobEvent(this, work), event ->
      event.getEmployee().addWork(event.getWork()));
  }

  /**
   * closes the employee, runs when the player quits from the server.
   *
   * @return completed future.
   */
  @NotNull
  default CompletableFuture<Void> close() {
    return Employee.close(this);
  }

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
   * gets the player name if its not null otherwise, returns unique id as text.
   *
   * @return player name or unique id.
   */
  @NotNull
  default String getPlayerName() {
    final var name = this.getOfflinePlayer().getName();
    return name == null ? this.getPlayerUniqueId().toString() : name;
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
  default Optional<Work> getWork(@NotNull final String id) {
    return Job.get(id)
      .flatMap(this::getWork);
  }

  /**
   * gets the work.
   *
   * @param work the work to get.
   *
   * @return work.
   */
  @NotNull
  default Optional<Work> getWork(@NotNull final Work work) {
    return this.getWork(work.getJob());
  }

  /**
   * gets the work.
   *
   * @param job the job to get.
   *
   * @return work.
   */
  @NotNull
  Optional<Work> getWork(@NotNull Job job);

  /**
   * obtains the work limit.
   *
   * @return work limit.
   */
  long getWorkLimit();

  /**
   * sets the work limit.
   *
   * @param limit the limit to set.
   */
  void setWorkLimit(long limit);

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
   * @param work the work to remove.
   */
  void removeWork(@NotNull Work work);

  /**
   * removes the work.
   *
   * @param id the id to remove.
   *
   * @return {@code true} if player successfully quit from the work.
   */
  default boolean removeWorkWithEvent(@NotNull final String id) {
    return Job.get(id)
      .map(this::removeWorkWithEvent)
      .orElse(false);
  }

  /**
   * removes the work.
   *
   * @param job the job to remove.
   *
   * @return {@code true} if player successfully quit from the work.
   */
  default boolean removeWorkWithEvent(@NotNull final Job job) {
    return this.getWork(job)
      .map(this::removeWorkWithEvent)
      .orElse(false);
  }

  /**
   * removes the work.
   *
   * @param work the work to remove.
   *
   * @return {@code true} if player successfully quit from the work.
   */
  default boolean removeWorkWithEvent(@NotNull final Work work) {
    return Callable.callEvent(new EmployeeQuitJobEvent(this, work), event ->
      event.getEmployee().removeWork(event.getWork()));
  }

  /**
   * saves the employee.
   *
   * @return completed future.
   */
  @NotNull
  default CompletableFuture<Void> save() {
    return Employee.save(this);
  }
}
