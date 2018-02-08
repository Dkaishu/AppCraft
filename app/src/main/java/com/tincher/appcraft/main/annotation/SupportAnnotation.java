package com.tincher.appcraft.main.annotation;

import android.Manifest;
import android.net.Uri;
import android.support.annotation.AnimRes;
import android.support.annotation.AnimatorRes;
import android.support.annotation.AnyRes;
import android.support.annotation.AttrRes;
import android.support.annotation.BinderThread;
import android.support.annotation.BoolRes;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.IntegerRes;
import android.support.annotation.InterpolatorRes;
import android.support.annotation.Keep;
import android.support.annotation.LayoutRes;
import android.support.annotation.MainThread;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.annotation.StringDef;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.annotation.StyleableRes;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.support.annotation.XmlRes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import static android.app.ActionBar.DISPLAY_HOME_AS_UP;
import static android.app.ActionBar.DISPLAY_SHOW_HOME;
import static android.app.ActionBar.DISPLAY_SHOW_TITLE;
import static android.app.ActionBar.DISPLAY_USE_LOGO;

/**
 * Support Annotation Lib 的使用
 * <p>
 * Created by dks on 2018/2/7.
 */

public class SupportAnnotation {
    /**
     * Nullness注解
     */
    @NonNull
    String sayHello(@NonNull String helloWorld) {
        return helloWorld;
    }

    @Nullable
    String saySomething(@Nullable String helloWorld) {
        return helloWorld;
    }

    /**
     * 资源类型注解
     */

    {
        @AnyRes int resId1;//android.R. 任意资源类型
        @LayoutRes int resId0;//android.R.layout 类型
        @ColorRes int resId2;//android.R.color 类型
        @IdRes int resId3;//android.R.id 类型
        @MenuRes int resId4;//android.R.menu 类型
        @RawRes int resId5;//android.R.raw 类型
        @StringRes int resId6;//android.R.string 类型
        @StyleRes int resId7;//android.R.style 类型
        @StyleableRes int resId8;//android.R.styleable 类型
        @DrawableRes int resId9;//android.R.drawable 类型
        @AttrRes int resId10;//android.R.attr 类型
        @AnimRes int resId11;//android.R.anim 类型
        @AnimatorRes int resId12;//android.R.animator 类型
        @BoolRes int resId13;// boolean 类型
        @IntegerRes int resId14;//android.R.integer 类型
        @InterpolatorRes int resId15;//android.R.interpolator 类型
        @XmlRes int resId16;//android.R.xml 类型
//        @TransitionRes int resId17;//Transition 类型
//      ...
    }

    /**
     * 类型定义注解
     */

    public static final int    INT1 = 1;
    public static final int    INt2 = 2;
    public static final String STR1 = "1";
    public static final String STR2 = "3";

    @Retention(RetentionPolicy.SOURCE)//元注解,告知编译器不要在.class文件中存储注解数据
    @IntDef({INT1, INt2})//定义可接受的常量列表
    public @interface availableInt {
    }

    @StringDef({STR1, STR2})
    public @interface availableStr {
    }

    @availableInt
    public int getAvailableInt(@availableStr String availableStr) {
        return Integer.valueOf(availableStr);
    }


    //指定一个整型是一个标记性质的类型；这样客户端代码就通过|，&等操作符同时传递多个常量了
    @IntDef(flag = true, value = {
            DISPLAY_USE_LOGO,
            DISPLAY_SHOW_HOME,
            DISPLAY_HOME_AS_UP,
            DISPLAY_SHOW_TITLE
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface DisplayOptions {
    }

    public void setDisplayOptions(@DisplayOptions int mode) {
    }

    {
        setDisplayOptions(DISPLAY_USE_LOGO | DISPLAY_SHOW_HOME);
    }

    /**
     * 线程注解
     */

    //标记运行在UI线程，一个UI线程是Activity运行所在的主窗口，对于一个应用而言，可能存在多个UI线程，
    //每个UI线程对应不同的主窗口
    @UiThread
    protected void ranOnUiThread() {
    }

    //标记运行在主线程，一个应用只有一个主线程，主线程也是一个UI线程。同常情况下，用@MainThread标注生命周期相关函数，
    //用@UiThread标记视图关系函数
    @MainThread
    protected void ranOnMainThread() {
    }

    //标记运行在后台线程
    @WorkerThread
    protected void ranOnWorkerThread() {
    }

    //标记运行在Binder线程（启动binder进行通信的线程）
    @BinderThread
    protected void ranOnBinderThread() {
    }

    /**
     * RGB/ARGB颜色注解
     */
    @ColorInt
    int color;


    /**
     * 值范围注解
     */
    //对于数组、集合、字符串 ，用@Size表示其大小

    @Size(min = 2)
    ArrayList list;//list的size最小为2

    @Size(max = 10)
    String s;//s的最大字符个数为10

    @Size(multiple = 3)
    int[] ints;//数组大小是3的倍数

    @IntRange(from = 1, to = 20)
    int intRange;//intRange的取值范围为1-20,适用于int和long类型

    @FloatRange(from = 0.0, to = 6.0)
    float floatRange;//floatRange的取值范围为0.0-6.0，适用于float和double类型

    /**
     * 权限注解
     */

    //目的是为了在编译时及时发现缺失的权限

    //某个特定权限
    @RequiresPermission(Manifest.permission.SET_WALLPAPER)
    void setWallpaper() {
    }


    //至少需要权限集合中的一个，使用anyOf属性：
    @RequiresPermission(anyOf = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION})
    void getLocation() {
    }

    //同时需要多个权限，用allOf属性：

    @RequiresPermission(allOf = {
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_CONTACTS})
    void updateVisitedHistory() {
    }


    //对于intents的权限，可以直接在定义的intent常量字符串字段上标注权限需求(他们通常都已经被@SdkConstant注解标注过了):
    @RequiresPermission(android.Manifest.permission.BLUETOOTH)
    public static final String ACTION_REQUEST = "android.bluetooth.adapter.action.REQUEST_DISCOVERABLE";


    //对于content providers的权限，你可能需要单独的标注读和写的权限访问，所以可以用@Read或者@Write标注每一个权限需求：
    @RequiresPermission.Read(@RequiresPermission(Manifest.permission.ACCESS_COARSE_LOCATION))
    @RequiresPermission.Write(@RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION))
    public static final Uri PROVIDER_URI = Uri.parse("content://...");


    /**
     * 重写函数注解
     *
     * 如果你的API允许使用者重写你的方法，但是，你又需要你自己的方法(父方法)在重写的时候也被调用，@CallSuper给重写者提示
     */
    @CallSuper
    void onCreate(){}


    /**
     * 返回值注解
     */
//    如果你的方法返回一个值，你期望调用者用这个值做些事情，那么你可以使用@CheckResult注解标注这个方法。
//
//    你并不需要微每个非空方法都进行标注。它主要的目的是帮助哪些容易被混淆，难以被理解的API的使用者。
//
//    比如，可能很多开发者都对String.trim()一知半解，认为调用了这个方法，就可以让字符串改变以去掉空白字符。
//    如果这个方法被@CheckResult标注，工具就会对那些没有使用trim()返回结果的调用者发出警告。
//
//    Android中，Context#checkPermission这个方法已经被@CheckResult标注了：

    @CheckResult(suggest="#enforcePermission(String,int,int,String)")
    public int checkPermission(@NonNull String permission, int pid, int uid){return 0;}


    /**
     * 测试可见注解
     */
    // 单元测试中可能需要访问一些不可见的类，函数，变量等，可以用 @VisibleForTesting 使其对测试可见
    @VisibleForTesting
    void test(){}


    /**
     *防止混淆
     */
    //被这个注解标注的类和方法在proguard混淆的时候将不会被混淆。
    @Keep
    void myKeepMethod(){}


}
