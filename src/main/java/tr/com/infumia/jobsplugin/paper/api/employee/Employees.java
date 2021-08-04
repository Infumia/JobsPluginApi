package tr.com.infumia.jobsplugin.paper.api.employee;

import java.util.Collection;
import java.util.HashSet;
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
 * an utility class that contains methods for {@link Employee}.
 */
@UtilityClass
public class Employees {

  /**
   * the employees by unique id.
   */
  private final Map<UUID, Employee> EMPLOYEES = new ConcurrentHashMap<>();

  /**
   * the employees.
   */
  private final Collection<Employee> EMPLOYEES_SET = new HashSet<>();

  /**
   * the employee creator.
   */
  @Nullable
  private EmployeeCreator employeeCreator;

  /**
   * closes the employee, runs when the player quits from the server.
   *
   * @param employee the employee to close.
   */
  void close(@NotNull final Employee employee) {
    Employees.EMPLOYEES.remove(employee.getPlayerUniqueId());
    Employees.EMPLOYEES_SET.remove(employee);
  }

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
        Employees.EMPLOYEES_SET.add(employee);
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
        Employees.provideEmployee(uniqueId).whenComplete((employee, throwable) -> {
          final var newly = Objects.requireNonNullElseGet(employee, () -> Employees.createEmployee(uniqueId));
          Employees.EMPLOYEES.put(uniqueId, newly);
          Employees.EMPLOYEES_SET.add(newly);
        }));
  }

  /**
   * saves the employee.
   *
   * @param employee the employee to save.
   *
   * @return completed future.
   */
  @NotNull
  static CompletableFuture<Void> save(@NotNull final Employee employee) {
    if (!Employees.EMPLOYEES_SET.contains(employee)) {
      Employees.EMPLOYEES.put(employee.getPlayerUniqueId(), employee);
      Employees.EMPLOYEES_SET.add(employee);
    }
    return Employees.supplyEmployee(employee);
  }

  /**
   * saves all the employees.
   *
   * @return completed future.
   */
  @NotNull
  static CompletableFuture<Void> saveAll() {
    return Employees.supplyAllEmployees(Employees.EMPLOYEES_SET);
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

  /**
   * supplies all the employees.
   *
   * @param employees the employees to supply.
   *
   * @return completable future.
   */
  @NotNull
  private CompletableFuture<Void> supplyAllEmployees(@NotNull final Collection<Employee> employees) {
    return CompletableFuture.runAsync(() -> Employees.getEmployeeCreator().supplyAllEmployee(employees));
  }

  /**
   * supplies the employee.
   *
   * @param employee the employee to supply.
   *
   * @return completed future..
   */
  @NotNull
  private CompletableFuture<Void> supplyEmployee(@NotNull final Employee employee) {
    return CompletableFuture.runAsync(() -> Employees.getEmployeeCreator().supplyEmployee(employee));
  }
}
