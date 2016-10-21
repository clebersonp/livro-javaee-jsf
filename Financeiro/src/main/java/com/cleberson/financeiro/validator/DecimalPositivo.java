package com.cleberson.financeiro.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * Anotação criada para validar 2 condições, notnull e decimalmin
 * @author Cleberson
 *
 */

@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER, CONSTRUCTOR, ANNOTATION_TYPE })
@Constraint(validatedBy = {})
@DecimalMin("0")
@NotNull
public @interface DecimalPositivo {
	@OverridesAttribute(constraint = DecimalMin.class, name = "message")
	// essa chave será referenciada no arquivo ValidationMessages.properties
	String message() default "{com.cleberson.financeiro.validator.NumeroDecimal.messge}";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
