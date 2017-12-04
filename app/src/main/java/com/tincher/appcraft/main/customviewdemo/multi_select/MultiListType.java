package com.tincher.appcraft.main.customviewdemo.multi_select;

import java.util.List;

/**
 * Created by dks on 2017/12/4.
 */

public class MultiListType {
    private List<MultiBase> multiBases;

    public List<MultiBase> getMultiBases() {
        return multiBases;
    }

    public void setMultiBases(List<MultiBase> multiBases) {
        this.multiBases = multiBases;
    }

    public static class Type1 implements MultiBase {
        private String subtitle;
        private List<TypeBase> itemList;

    }

    public static class Type2 implements MultiBase {

    }

    public static class Type3 implements MultiBase {

    }

    public static class Type4 implements MultiBase {

    }

    public static class TypeBase implements MultiBase {
       private String itemName;

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        @Override
        public String toString() {
            return "TypeBase{" +
                    "itemName='" + itemName + '\'' +
                    '}';
        }
    }
}
