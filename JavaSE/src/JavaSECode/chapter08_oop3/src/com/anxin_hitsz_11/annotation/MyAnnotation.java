package com.anxin_hitsz_11.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * ClassName: MyAnnotation
 * Package: com.anxin_hitsz_11.annotation
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/2 17:05
 * @Version 1.0
 */
@Target({TYPE, FIELD, METHOD, CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String value() default "hello";
}
