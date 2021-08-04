package tr.com.infumia.jobsplugin.paper.api.event;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.player.Employee;

/**
 * a class that represents employee load events which fire when a player joins to the server and loads employee date.
 * <p>
 * this event is async.
 */
public final class EmployeeLoadEvent extends EmployeeEvent {

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
  public EmployeeLoadEvent(@NotNull final Employee employee) {
    super(true, employee);
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return EmployeeLoadEvent.handlerList;
  }
}
