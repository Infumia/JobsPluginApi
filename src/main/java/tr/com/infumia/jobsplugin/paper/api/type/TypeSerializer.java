package tr.com.infumia.jobsplugin.paper.api.type;

import org.jetbrains.annotations.NotNull;
import tr.com.infumia.infumialib.transformer.TransformedData;

/**
 * an interface to determine type serializers.
 */
public interface TypeSerializer {

  /**
   * serializes the type.
   *
   * @param data the data to serialize.
   */
  void serialize(@NotNull TransformedData data);
}
