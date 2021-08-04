package tr.com.infumia.jobsplugin.paper.api.player;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a utility class that contains methods for {@link Employee}.
 */
@UtilityClass
public class Employees {

  /**
   * the employees by unique id.
   */
  private final Map<UUID, Employee> EMPLOYEES = new ConcurrentHashMap<>();

  /**
   * the employee creator.
   */
  @Nullable
  private EmployeeCreator employeeCreator;

  /**
   * gets employee or creates it.
   *
   * @param uniqueId the unique id to get.
   *
   * @return employee.
   */
  @NotNull
  Employee getOrCreate(@NotNull final UUID uniqueId) {
    return Optional.ofNullable(Employees.EMPLOYEES.get(uniqueId))
      .orElseGet(() -> {
        final var employee = Employees.createEmployee(uniqueId);
        Employees.EMPLOYEES.put(uniqueId, employee);
        return employee;
      });
  }

  /**
   * loads the employee if exist otherwise, creates and returns.
   *
   * @param uniqueId the unique id to get.
   *
   * @return employee.
   */
  @NotNull
  CompletableFuture<@NotNull Employee> load(@NotNull final UUID uniqueId) {
    return Optional.ofNullable(Employees.EMPLOYEES.get(uniqueId))
      .map(CompletableFuture::completedFuture)
      .orElseGet(() ->
        Employees.provideEmployee(uniqueId).whenComplete((employee, throwable) ->
          Employees.EMPLOYEES.put(
            uniqueId,
            Objects.requireNonNullElseGet(employee, () -> Employees.createEmployee(uniqueId)))));
  }

  /**
   * creates a new instance for {@link Employee}.
   *
   * @param uniqueId the unique id to create.
   *
   * @return a newly created employee.
   */
  @NotNull
  private Employee createEmployee(@NotNull final UUID uniqueId) {
    return Employees.getEmployeeCreator().createEmployee(uniqueId);
  }

  /**
   * gets the employee creator.
   *
   * @return employee creator.
   *
   * @throws NullPointerException if {@link #employeeCreator} is {@code null}.
   */
  @NotNull
  private EmployeeCreator getEmployeeCreator() {
    return Objects.requireNonNull(Employees.employeeCreator, "employee creator");
  }

  /**
   * sets the employee creator if it's not set already.
   *
   * @param employeeCreator the employee creator to set.
   */
  public void setEmployeeCreator(@NotNull final EmployeeCreator employeeCreator) {
    if (Employees.employeeCreator == null) {
      Employees.employeeCreator = employeeCreator;
    }
  }

  /**
   * provides an employee via unique id.
   *
   * @param uniqueId the unique id to provide.
   *
   * @return provided employee.
   */
  @NotNull
  private CompletableFuture<@Nullable Employee> provideEmployee(@NotNull final UUID uniqueId) {
    return CompletableFuture.supplyAsync(() -> Employees.getEmployeeCreator().provideEmployee(uniqueId));
  }
}
