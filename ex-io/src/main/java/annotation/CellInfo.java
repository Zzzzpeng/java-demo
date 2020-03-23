package annotation;

import org.apache.poi.ss.formula.functions.T;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CellInfo {
    String format() default "";
    Class type() default String.class;
    int width() default 0;
}
