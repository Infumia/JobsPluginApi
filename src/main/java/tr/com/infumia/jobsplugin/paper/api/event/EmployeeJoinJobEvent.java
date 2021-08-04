package tr.com.infumia.jobsplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.player.Employee;
import tr.com.infumia.jobsplugin.paper.api.player.Work;

/**
 * a class that represents employee join job events which fire when a player joins to a job.
 */
public final class EmployeeJoinJobEvent extends EmployeeEvent {

  /**
   * the handler list.
   */
  @Getter
  private static final HandlerList handlerList = new HandlerList();

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
   */
  public EmployeeJoinJobEvent(@NotNull final Employee employee, @NotNull final Work work) {
    super(employee);
    this.work = work;
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return EmployeeJoinJobEvent.handlerList;
  }
}
