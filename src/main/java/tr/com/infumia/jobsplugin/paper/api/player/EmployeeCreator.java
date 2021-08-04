package tr.com.infumia.jobsplugin.paper.api.player;

import java.util.Collection;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * an interface to determine employee creator.
 */
public interface EmployeeCreator {

  /**
   * creates a new instance for {@link Employee}.
   *
   * @param uniqueId the unique id to create.
   *
   * @return a newly created employee.
   */
  @NotNull
  Employee createEmployee(@NotNull UUID uniqueId);

  /**
   * provides an employee.
   *
   * @param uniqueId the unique id to provide.
   *
   * @return provided employee.
   */
  @Nullable
  Employee provideEmployee(@NotNull UUID uniqueId);

  /**
   * supplies all employees.
   *
   * @param employees the employees to supply.
   */
  void supplyAllEmployee(@NotNull Collection<Employee> employees);

  /**
   * supplies the employee.
   *
   * @param employee the employee to supply.
   */
  void supplyEmployee(@NotNull Employee employee);
}
