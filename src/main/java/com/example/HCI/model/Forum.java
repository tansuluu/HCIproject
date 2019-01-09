package com.example.HCI.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "forum")
public class Forum {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "user", length = 7000)
    private String user;
    @Column(name = "text", length = 7000)
    private String text;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    private Date date;

    public Forum() {
    }

    public Forum(String user, String text, Date date) {
        this.user = user;
        this.text = text;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
