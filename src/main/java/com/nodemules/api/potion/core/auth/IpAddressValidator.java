package com.nodemules.api.potion.core.auth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

/**
 * @author brent
 * @since 12/15/17.
 */
@Component
public class IpAddressValidator implements ConstraintValidator<IpAddress, Object> {

  private static final String PATTERN_0_255 = "[01]?\\d\\d?|2[0-4]\\d|25[0-5]";
  private static final String PATTERN_IPV4_ADDRESS = String
      .format("^(%s)\\.(%s)\\.(%s)\\.(%s)$", PATTERN_0_255, PATTERN_0_255, PATTERN_0_255,
          PATTERN_0_255);
  private Pattern pattern;

  @Override
  public void initialize(IpAddress constraintAnnotation) {
    pattern = Pattern.compile(PATTERN_IPV4_ADDRESS);
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext cvContext) {
    String ip = (String) value;
    Matcher matcher = pattern.matcher(ip);
    return matcher.matches();
  }
}