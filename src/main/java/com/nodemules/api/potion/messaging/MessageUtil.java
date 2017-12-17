package com.nodemules.api.potion.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;

/**
 * @author brent
 * @since 12/17/17.
 */
@Slf4j
public final class MessageUtil {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  private MessageUtil() {
    /* empty private constructor */
  }

  public static <T> T parse(Message message, Class<T> type) {
    T parsed = null;
    try {
      parsed = objectMapper.readValue(message.getBody(), type);
    } catch (IOException e) {
      log.error("Error parsing Message from type: {}", type.getName());
    }
    return parsed;
  }

  public static <T> byte[] writeBody(T content) {
    byte[] bytes = new byte[0];
    try {
      bytes = objectMapper.writeValueAsBytes(content);
    } catch (JsonProcessingException e) {
      log.error("Unable to write content");
    }
    return bytes;
  }
}
