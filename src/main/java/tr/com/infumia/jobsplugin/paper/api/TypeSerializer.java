package tr.com.infumia.jobsplugin.paper.api;

import org.jetbrains.annotations.NotNull;
import tr.com.infumia.infumialib.transformer.ObjectSerializer;
import tr.com.infumia.infumialib.transformer.TransformedData;

/**
 * an interface to determine type serializers.
 */
public interface TypeSerializer<T> {

  /**
   * obtains the deserializer.
   *
   * @return deserializer.
   */
  @NotNull
  ObjectSerializer<T> getDeserializer();

  /**
   * serializes the type.
   *
   * @param data the data to serialize.
   */
  void serialize(@NotNull TransformedData data);
}
