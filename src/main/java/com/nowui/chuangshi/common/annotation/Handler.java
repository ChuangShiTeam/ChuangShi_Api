package com.nowui.chuangshi.common.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface Handler {

    String tag() default "";

    int thread_max() default 1;

    int thread_min() default 1;

}
