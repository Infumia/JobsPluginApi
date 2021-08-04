package tr.com.infumia.jobsplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.player.Employee;
import tr.com.infumia.jobsplugin.paper.api.player.Work;

/**
 * a class that represents employee job level change events which fire when level of the player's job change.
 */
public final class EmployeeJobLevelChangeEvent extends EmployeeEvent {

  /**
   * the handler list.
   */
  @Getter
  private static final HandlerList handlerList = new HandlerList();

  /**
   * the level.
   */
  @Getter
  @Setter
  private long level;

  /**
   * the work.
   */
  @NotNull
  @Getter
  @Setter
  private Work work;

  /**
   * ctor.
   *
   * @param employee the employee.
   * @param work the work.
   * @param level the level.
   */
  public EmployeeJobLevelChangeEvent(@NotNull final Employee employee, @NotNull final Work work, final long level) {
    super(employee);
    this.work = work;
    this.level = level;
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return EmployeeJobLevelChangeEvent.handlerList;
  }
}
