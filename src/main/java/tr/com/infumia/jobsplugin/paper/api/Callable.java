package tr.com.infumia.jobsplugin.paper.api;

import java.util.function.Consumer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine callables.
 */
public interface Callable {

  /**
   * calls the given event then, if it's succeed runs the consumer.
   *
   * @param event the event to call.
   * @param consumer the consumer to call.
   * @param <E> type of the event.
   *
   * @return {@code true} if the event called successfully.
   */
  static <E extends Event> boolean callEvent(@NotNull final E event, @NotNull final Consumer<E> consumer) {
    if (event.callEvent()) {
      consumer.accept(event);
      return true;
    }
    return false;
  }
}
