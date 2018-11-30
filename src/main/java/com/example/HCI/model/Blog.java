package com.example.HCI.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "title", length = 128)
    private String title;
    @Column(name = "text")
    @NotEmpty(message = "*Please write post")
    private String text;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateBlog", nullable = false)
    private Date dateBlog;
    @Column(name = "category", length = 128)
    private String category;
    @Column(name = "photo1", length = 200)
    private String photo1;
    @Column(name = "photo2", length = 200)
    private String photo2;
    @Column(name = "photo3", length = 36)
    private String photo3;
}
