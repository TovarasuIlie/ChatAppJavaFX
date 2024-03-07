package com.example.chatappjavafx.BaseClasses;

import com.example.chatappjavafx.BaseClasses.Enum.MessageType;

public class ChatMessage {
    private String content;
    private String sender;
    private MessageType messageType;

    public ChatMessage(String content, String sender, MessageType messageType) {
        this.content = content;
        this.sender = sender;
        this.messageType = messageType;
    }

    public ChatMessage() {
        this.content = null;
        this.sender = null;
        this.messageType = null;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
