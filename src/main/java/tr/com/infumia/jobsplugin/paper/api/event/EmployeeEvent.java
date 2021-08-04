package tr.com.infumia.jobsplugin.paper.api.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.jobsplugin.paper.api.player.Employee;

/**
 * an abstract class that represents employee events.
 */
abstract class EmployeeEvent extends Event {

  /**
   * the employee.
   */
  @NotNull
  @Getter
  @Setter
  private Employee employee;

  /**
   * ctor.
   *
   * @param employee the employee.
   */
  EmployeeEvent(@NotNull final Employee employee) {
    this.employee = employee;
  }

  /**
   * ctor.
   *
   * @param isAsync the is async.
   * @param employee the employee.
   */
  EmployeeEvent(final boolean isAsync, @NotNull final Employee employee) {
    super(isAsync);
    this.employee = employee;
  }
}
