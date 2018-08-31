package com.bazalytskyi.test.data.dto;

import java.util.Objects;


public class BlogPostDTO {
    private int id;
    private String title;
    private String content;
    private int publisherId;
    private String publisher;

    public BlogPostDTO(String title, String content, String publisher, int publisherId) {
        this.title = title;
        this.content = content;
        this.publisher = publisher;
        this.publisherId = publisherId;
    }

    public BlogPostDTO() {
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogPostDTO that = (BlogPostDTO) o;
        return id == that.id &&
                publisherId == that.publisherId &&
                Objects.equals(title, that.title) &&
                Objects.equals(content, that.content) &&
                Objects.equals(publisher, that.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, publisherId, publisher);
    }

    @Override
    public String toString() {
        return "BlogPostDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publisherId=" + publisherId +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
