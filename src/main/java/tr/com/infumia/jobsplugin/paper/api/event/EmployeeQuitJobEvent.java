package tr.com.infumia.jobsplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.employee.Employee;
import tr.com.infumia.jobsplugin.paper.api.work.Work;

/**
 * a class that represents employee quit job events which fire when a player quits from a job.
 */
public final class EmployeeQuitJobEvent extends EmployeeEvent {

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
  public EmployeeQuitJobEvent(@NotNull final Employee employee, @NotNull final Work work) {
    super(employee);
    this.work = work;
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return EmployeeQuitJobEvent.handlerList;
  }
}
