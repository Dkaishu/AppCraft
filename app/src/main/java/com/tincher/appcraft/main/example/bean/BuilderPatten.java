package com.tincher.appcraft.main.example.bean;

/**
 * Created by dks on 2018/2/7.
 */

public class BuilderPatten {
    private final String mName;
    private final int    mAge;
    private final String mLocation;

    private BuilderPatten(Builder builder) {
        mName = builder.mName;
        mAge = builder.mAge;
        mLocation = builder.mLocation;
    }

    public static final class Builder {
        private final String mName; //必选
        private       int    mAge;  //可选
        private       String mLocation;

        public Builder(String mName) {
            this.mName = mName;
        }

        public Builder mAge(int val) {
            mAge = val;
            return this;
        }

        public Builder mLocation(String val) {
            mLocation = val;
            return this;
        }

        public BuilderPatten build() {
            return new BuilderPatten(this);
        }
    }


}
