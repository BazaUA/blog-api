package com.bazalytskyi.test.data.entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "BlogPosts")
public class BlogPost {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;
    @ManyToOne
    @JoinColumn(name = "blog_post_fk")
    private Publisher publisher;

    public BlogPost(String title, String content, Publisher publisher) {
        this.title = title;
        this.content = content;
        this.publisher = publisher;
    }

    public BlogPost() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogPost blogPost = (BlogPost) o;
        return id == blogPost.id &&
                Objects.equals(title, blogPost.title) &&
                Objects.equals(content, blogPost.content) &&
                Objects.equals(publisher, blogPost.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, publisher);
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publisher=" + publisher +
                '}';
    }
}
