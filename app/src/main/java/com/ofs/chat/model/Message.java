package com.ofs.chat.model;

/**
 * Created by saravana.subramanian on 3/6/17.
 */

public class Message {

    private String teamId;
    private String message;
    private String author;
    private String authorId;
    private String date;

    public Message(String message, String teamId, String author, String date) {
        this.message = message;
        this.teamId = teamId;
        this.author = author;
        this.date = date;
    }

    public Message(String teamId, String message, String author, String authorId, String date) {
        this.teamId = teamId;
        this.message = message;
        this.author = author;
        this.authorId = authorId;
        this.date = date;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
