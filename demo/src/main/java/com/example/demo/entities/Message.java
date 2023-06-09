package com.example.demo.entities;

import java.util.HashMap;

public class Message {
    private String name;
    private String text;

    public Message(){}

    public Message(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString(){
        return this.name + " : " + this.text;
    }
}
