package tr.com.infumia.jobsplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.employee.Employee;
import tr.com.infumia.jobsplugin.paper.api.mission.Mission;
import tr.com.infumia.jobsplugin.paper.api.work.Work;

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
   * the missions.
   */
  @NotNull
  @Getter
  @Setter
  private Mission mission;

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
   * @param mission the mission.
   * @param exp the exp.
   */
  public EmployeeJobExpChangeEvent(@NotNull final Employee employee, @NotNull final Work work,
                                   @NotNull final Mission mission, final long exp) {
    super(employee);
    this.work = work;
    this.mission = mission;
    this.exp = exp;
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return EmployeeJobExpChangeEvent.handlerList;
  }
}
