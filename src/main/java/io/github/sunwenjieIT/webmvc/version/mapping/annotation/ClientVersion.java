package io.github.sunwenjieIT.webmvc.version.mapping.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClientVersion {

    String minVersion() default "-1";
    String maxVersion() default "-1";
    int order() default -1;

}
