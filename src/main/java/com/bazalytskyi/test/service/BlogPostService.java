package com.bazalytskyi.test.service;

import java.util.ArrayList;
import java.util.List;

import com.bazalytskyi.test.dao.IBlogPostRepository;
import com.bazalytskyi.test.dao.IPublisherRepository;
import com.bazalytskyi.test.data.dto.BlogPostDTO;
import com.bazalytskyi.test.data.entity.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazalytskyi.test.data.entity.BlogPost;

@Service
public class BlogPostService implements IBlogPostService {
    @Autowired
    private IBlogPostRepository postRepository;
    @Autowired
    private IPublisherRepository publisherRepository;

    @Override
    public List<BlogPost> getAllBlogPosts() {
        return postRepository.getAllBlogPosts();
    }

    @Override
    public BlogPost getBlogPostById(int postId) {
        return postRepository.getBlogPostById(postId);
    }

    @Override
    public void addBlogPost(BlogPost blogPost) {
        postRepository.addBlogPost(blogPost);
    }

    @Override
    public void updateBlogPost(BlogPost blogPost) {
        postRepository.updateBlogPost(blogPost);
    }

    @Override
    public void deleteBlogPost(int postId) {
        postRepository.deleteBlogPost(postId);
    }

    @Override
    public boolean isExists(int id) {
        return postRepository.isExists(id);
    }

    @Override
    public BlogPost convertToBlogPost(BlogPostDTO blogPostDTO) {
        BlogPost res = new BlogPost();
        res.setTitle(blogPostDTO.getTitle());
        res.setId(blogPostDTO.getId());
        res.setContent(blogPostDTO.getContent());
        if (publisherRepository.exists(blogPostDTO.getPublisherId())) {
            res.setPublisher(publisherRepository.findOne(blogPostDTO.getPublisherId()));
        }
        return res;
    }

    @Override
    public BlogPostDTO convertToBlogPostDTO(BlogPost blogPost) {
        BlogPostDTO dto = new BlogPostDTO();
        dto.setId(blogPost.getId());
        dto.setContent(blogPost.getContent());
        dto.setTitle(blogPost.getTitle());
        dto.setPublisherId(blogPost.getPublisher().getId());
        dto.setPublisher(blogPost.getPublisher().getName());
        return dto;
    }

    @Override
    public List<BlogPostDTO> convertToBlogPostDTO(List<BlogPost> allBlogPosts) {
        List<BlogPostDTO> res = new ArrayList<>();
        for (BlogPost c : allBlogPosts) {
            res.add(convertToBlogPostDTO(c));
        }
        return res;
    }

    @Override
    public boolean isOwnPost(String currentPublisher, int id) {
        BlogPost post = getBlogPostById(id);
        Publisher publisher = publisherRepository.getPublisherByUsername(currentPublisher);
        if (post != null) {
            return publisher.getId() == post.getPublisher().getId() ? true : false;
        }
        return false;
    }

}
