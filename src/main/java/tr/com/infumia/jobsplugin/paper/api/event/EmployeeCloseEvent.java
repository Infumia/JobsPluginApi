package tr.com.infumia.jobsplugin.paper.api.event;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.employee.Employee;

/**
 * a class that represents employee close events which fire when a player quits from the server.
 * <p>
 * this event is async.
 */
public final class EmployeeCloseEvent extends EmployeeEvent {

  /**
   * the handler list.
   */
  @Getter
  private static final HandlerList handlerList = new HandlerList();

  /**
   * ctor.
   *
   * @param employee the employee.
   */
  public EmployeeCloseEvent(@NotNull final Employee employee) {
    super(true, employee);
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return EmployeeCloseEvent.handlerList;
  }
}
