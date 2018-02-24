package com.tincher.appcraft.main.annotation;

import android.support.annotation.IdRes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dks on 2018/2/8.
 *
 * java API 中默认定义的注解 即 标准注解
 * 按使用场景分 三类
 *
 */

public class JavaAnnotation {
    /**
     * 关于注解
     *
     * 注解也会编译成.class文件，就是创建一个实际的java接口
     * 接口中的方法 没有参数 没有throws 不能使用泛型
     * 接口中声明的方法 即是使用此注解时的参数
     *
     */
    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.FIELD)
    public @interface Bind{
        @IdRes
        int[] value();
    }

//    @Bind(R.id.text)
//    TextView text;

    /**
     * ********************** * 1.标准注解************************************
     */

    /**
     *
     * 给编译器使用
     */
//    @Override
//    告诉编译器 检查是否真的重载了父类的方法

//    @Deprecated
//    修饰不被鼓励使用或是废弃的方法、属性

//    @SuppressWarnings
//    用来抑制警告


//    @SafeVarargs
//    用于方法和构造函数，来断言不定长参数可以安全使用

//    @Generated
//    表明代码非手动编写

//    @FunctionalInterface
//    检查该接口是否仅包含一个 抽象 方法

    /**
     * 资源相关注解
     * 一般用于javaEE 领域
     */
//    @PostConstruct

//    @PreDestroy

//    @Resource

//    @Resources


    /**
     * 元注解
     * 用于定义注解的注解
     */

    //指定该注解所适用的对象范围，十种
    @Target({ElementType.PACKAGE , ElementType.TYPE})
    public @interface report{}


    //指明在什么级别保留该注解信息：.java源码；.class文件；Java虚拟机
    @Retention(RetentionPolicy.RUNTIME)
    public @interface re{}

    //指明该注解应该包含在被该注解修饰的文档中
    @Documented
    public @interface doc{}

    //指明该注解可以被子类继承
    @Inherited
    public @interface inh{}

    //指明该注解可在同一项上应用多次，（Java8 引入）
//    @Repeatable()
//    protected @interface rept{}


    /**
     * ********************** * 2.编译时注解************************************
     * 另：运行时注解：（@Retention(RetentionPolicy.RUNTIME)即可）
     */

    /**
     * 注解处理器，在编译期间，JVM会自动运行注解处理器（当然，我们需要将其注册）。
     * 虽然我们写的Java代码被编译成class就不能被改动了，但是注解处理器会重新生成其他的java代码，
     * 我们可以通过反射来调用新生成的java文件里面的类或方法。然后JVM再对这些生成的java代码进行编译。这是一个递归的过程。
     */





}


