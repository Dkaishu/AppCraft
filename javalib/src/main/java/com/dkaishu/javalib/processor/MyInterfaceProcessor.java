package com.dkaishu.javalib.processor;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * 注解处理器，在编译期间，JVM会自动运行注解处理器（当然，我们需要将其注册）。
 * 只能产生新的源文件，不能修改已经存在的源文件
 * http://blog.csdn.net/haveferrair/article/details/52182927
 * Created by dks on 2018/2/9.
 */

public class MyInterfaceProcessor extends AbstractProcessor {

    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;

    //init对一些工具及其他进行初始化。
    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        typeUtils = env.getTypeUtils();


        //理解 Element ：
        //      “package com.example;”是此类的PackageElement；
        //      “package com.example;”是此类的TypeElement；
        //      “private int a;”是此类的VariableElement；
        //      “public Foo () {}”是此类的ExecuteableElement；
        //      “public someMthod () {}”是此类的ExecuteableElement；
        //      ....
        elementUtils = env.getElementUtils();
        filer = env.getFiler();
        //注解处理器里面不可以抛出Exception,Message就是为了输出错误信息
        messager = env.getMessager();

    }

    @Override
    public boolean process(Set<? extends TypeElement> annoations, RoundEnvironment env) {
        Map<String, AnnotatedClass> classMap = new HashMap<>();

        // 得到所有注解@MyInterface的Element集合
        Set<? extends Element> elementSet = env.getElementsAnnotatedWith(MyInterface.class);

        for (Element e : elementSet) {
            //首先所有加上@Interface的Element进行变量，第一步判断注解是否加在Method之上，如果不是就输出错误信息。
            if (e.getKind() != ElementKind.METHOD) {
                error(e, "错误的注解类型，只有函数能够被该 @%s 注解处理", MyInterface.class.getSimpleName());
                return true;
            }

            ExecutableElement element = (ExecutableElement) e;
            AnnotatedMethod annotatedMethod = new AnnotatedMethod(element);

            String classname = annotatedMethod.getSimpleClassName();
            AnnotatedClass annotatedClass = classMap.get(classname);
            if (annotatedClass == null) {
                PackageElement pkg = elementUtils.getPackageOf(element);
                annotatedClass = new AnnotatedClass(pkg.getQualifiedName().toString(), element.getAnnotation(Interface.class).value());
                annotatedClass.addMethod(annotatedMethod);
                classMap.put(classname, annotatedClass);
            } else
                annotatedClass.addMethod(annotatedMethod);

        }
        // 代码生成
        for (AnnotatedClass annotatedClass : classMap.values()) {
            annotatedClass.generateCode(elementUtils, filer);
        }
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        //指定此注解处理器能够处理的注解类型，返回自己定义的注解的Set<String>
        Set<String> supportType = new LinkedHashSet<>();
        Set<String> types = new TreeSet<>();
        types.add(MyInterface.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}
