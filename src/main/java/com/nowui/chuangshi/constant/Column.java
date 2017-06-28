package com.nowui.chuangshi.constant;

import com.nowui.chuangshi.type.ColumnType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface Column {
    ColumnType type() default ColumnType.VARCHAR;

    int length() default 0;

    String defaultValue() default "";

    String comment() default "";
}