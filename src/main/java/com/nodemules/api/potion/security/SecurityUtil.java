package com.nodemules.api.potion.security;

import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author brent
 * @since 12/14/17.
 */
public final class SecurityUtil {

  private SecurityUtil() {
    throw new AssertionError();
  }

  public static Pair<String, String> decodeAuthorizationKey(final String basicAuthValue) {
    if (basicAuthValue == null) {
      return null;
    }
    final byte[] decodeBytes = Base64
        .decodeBase64(basicAuthValue.substring(basicAuthValue.indexOf(' ') + 1));
    String decoded;
    try {
      decoded = new String(decodeBytes, "UTF-8");
    } catch (final UnsupportedEncodingException e) {
      return null;
    }
    final int indexOfDelimiter = decoded.indexOf(':');
    final String username = decoded.substring(0, indexOfDelimiter);
    final String password = decoded.substring(indexOfDelimiter + 1);
    return new ImmutablePair<>(username, password);
  }
}
