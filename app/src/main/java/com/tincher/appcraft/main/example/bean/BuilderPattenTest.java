package com.tincher.appcraft.main.example.bean;

/**
 * Created by dks on 2018/2/7.
 */

public class BuilderPattenTest {
    BuilderPatten builderPatten = new BuilderPatten
            .Builder("name")
            .mAge(6)
            .mLocation("myLocation")
            .mLocation("")
            .build();
}
