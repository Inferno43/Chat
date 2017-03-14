package com.ofs.chat.model;

import java.util.ArrayList;

/**
 * Created by saravana.subramanian on 3/6/17.
 */

public class Team {

    private String name;
    private String teamId;
    private String image;
    private String adminId;
    private ArrayList<Employee> members;
    private ArrayList<Message> messages;

    public Team(String name, String image, String adminId, ArrayList<Employee> members) {
        this.name = name;
        this.image = image;
        this.adminId = adminId;
        this.members = members;
    }

    public Team(String name, String teamId, String image, String adminId, ArrayList<Employee> members) {
        this.name = name;
        this.teamId = teamId;
        this.image = image;
        this.adminId = adminId;
        this.members = members;
    }

    public Team(String name, String teamId, String image, String adminId, ArrayList<Employee> members, ArrayList<Message> messages) {
        this.name = name;
        this.teamId = teamId;
        this.image = image;
        this.adminId = adminId;
        this.members = members;
        this.messages = messages;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public ArrayList<Employee> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Employee> members) {
        this.members = members;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
