package com.bazalytskyi.test.service;

import java.util.List;

import com.bazalytskyi.test.data.dto.BlogPostDTO;
import com.bazalytskyi.test.data.entity.BlogPost;
import com.bazalytskyi.test.data.entity.Publisher;

public interface IBlogPostService {
    List<BlogPost> getAllBlogPosts();

    BlogPost getBlogPostById(int productId);

    void addBlogPost(BlogPost blogPost);

    void updateBlogPost(BlogPost blogPost);

    void deleteBlogPost(int productId);

    boolean isExists(int id);

    BlogPost convertToBlogPost(BlogPostDTO blogPostDTO);

    BlogPostDTO convertToBlogPostDTO(BlogPost blogPost);

    List<BlogPostDTO> convertToBlogPostDTO(List<BlogPost> allBlogPosts);

    boolean isOwnPost(String currentPublisher, int id);
}
