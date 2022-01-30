package com.cqnu.dlzf.utils.enums;

public enum Owner {

    I(1,"本用户"), Friend(0,"好友");

    // 方向下标
    private int index;

    // 方向名
    private String name;

    private Owner(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
