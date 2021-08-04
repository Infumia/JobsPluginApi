package tr.com.infumia.jobsplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.player.Employee;
import tr.com.infumia.jobsplugin.paper.api.player.Work;

/**
 * a class that represents employee job exp change events which fire when exp of the player's job change.
 */
public final class EmployeeJobExpChangeEvent extends EmployeeEvent {

  /**
   * the handler list.
   */
  @Getter
  private static final HandlerList handlerList = new HandlerList();

  /**
   * the exp.
   */
  @Getter
  @Setter
  private long exp;

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
   * @param exp the exp.
   */
  public EmployeeJobExpChangeEvent(@NotNull final Employee employee, @NotNull final Work work, final long exp) {
    super(employee);
    this.work = work;
    this.exp = exp;
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return EmployeeJobExpChangeEvent.handlerList;
  }
}
