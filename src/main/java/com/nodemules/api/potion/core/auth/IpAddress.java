package com.nodemules.api.potion.core.auth;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author brent
 * @since 12/15/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Documented
@Constraint(validatedBy = IpAddressValidator.class)
public @interface IpAddress {

  String message() default "Not a valid IPv4 Address";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
