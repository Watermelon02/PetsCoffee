package com.example.petscoffee.message;

public class Message {//firstActivity界面的message类
    private String content;//消息内容
    private MsgType msgType;//消息种类（左边还是右边，为枚举类）

    public Message(String content,MsgType msgType){
        this.content = content;
        this.msgType = msgType;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public String getContent() {
        return content;
    }
}

