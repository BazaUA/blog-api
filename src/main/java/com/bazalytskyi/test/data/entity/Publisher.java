package com.bazalytskyi.test.data.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique=true)
    private String username;
    private String password;
    private boolean enabled;
    private String name;
    @OneToMany(mappedBy = "publisher")
    private List<BlogPost> blogPosts;

    public Publisher(){}

    public Publisher(String username, String name){
        this.username = username;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BlogPost> getBlogPosts() {
        return blogPosts;
    }

    public void setBlogPosts(List<BlogPost> blogPosts) {
        this.blogPosts = blogPosts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return id == publisher.id &&
                enabled == publisher.enabled &&
                Objects.equals(username, publisher.username) &&
                Objects.equals(password, publisher.password) &&
                Objects.equals(name, publisher.name) &&
                Objects.equals(blogPosts, publisher.blogPosts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, enabled, name, blogPosts);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", enabled=" + enabled +
                ", name='" + name + '\'' +
                ", blogPosts=" + blogPosts +
                '}';
    }
}
