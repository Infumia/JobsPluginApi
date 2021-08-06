package tr.com.infumia.jobsplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.Mission;
import tr.com.infumia.jobsplugin.paper.api.employee.Employee;
import tr.com.infumia.jobsplugin.paper.api.employee.Work;

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
   * @param level the level.
   */
  public EmployeeJobLevelChangeEvent(@NotNull final Employee employee, @NotNull final Work work,
                                     @NotNull final Mission mission, final long level) {
    super(employee);
    this.work = work;
    this.mission = mission;
    this.level = level;
  }

  @NotNull
  @Override
  public HandlerList getHandlers() {
    return EmployeeJobLevelChangeEvent.handlerList;
  }
}
