package com.example.HCI.model;


import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Likes {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "username",length = 128)
    private String username;
    @Column(name = "appId")
    private long appId;

    public Likes() {
    }

    public Likes(String username, long app_id) {
        this.username = username;
        this.appId = app_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }
}
