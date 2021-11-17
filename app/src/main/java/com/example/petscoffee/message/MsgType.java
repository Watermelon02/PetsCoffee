package com.example.petscoffee.message;

public enum MsgType {//消息类型枚举类
    LEFT(0), RIGHT(1);
    private int type;

    private MsgType(int type) {
        this.setType(type);
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
}