package com.dkaishu.javalib.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018/2/24.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface MyInterface {
    String value();
}
