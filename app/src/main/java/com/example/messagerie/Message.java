package com.example.messagerie;

public class Message {
private String sender,mess,time;
private int id;

public Message(){}

    public Message(String mess, String sender, int id) {
        this.sender = sender;
        this.mess = mess;
        this.id = id;
    }

    public Message(String mess, String sender, String time) {
        this.time=time;
        this.sender = sender;
        this.mess = mess;
    }

    public Message(String mess, String sender,String time, int id) {
        this.sender = sender;
        this.time =time;
        this.mess = mess;
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
