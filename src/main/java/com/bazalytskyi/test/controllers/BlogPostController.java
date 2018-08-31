package com.bazalytskyi.test.controllers;

import java.util.List;

import com.bazalytskyi.test.data.dto.BlogPostDTO;
import com.bazalytskyi.test.data.entity.BlogPost;
import com.bazalytskyi.test.data.entity.Publisher;
import com.bazalytskyi.test.service.IBlogPostService;
import com.bazalytskyi.test.service.IPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.bazalytskyi.test.service.BlogPostService;

@RestController
@RequestMapping("/api")
public class BlogPostController {
    @Autowired
    private IBlogPostService blogPostService;
    @Autowired
    private IPublisherService publisherService;

    @GetMapping("/posts")
    public ResponseEntity<List<BlogPostDTO>> getAllBlogPost(@RequestParam(value = "own", required = false) boolean isOwn) {
        if(isOwn) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = (String) authentication.getName();
            Publisher currentPublisher = publisherService.getPublisherByUsername(username);
            List<BlogPostDTO> result = blogPostService.convertToBlogPostDTO(currentPublisher.getBlogPosts());
            return new ResponseEntity<List<BlogPostDTO>>(result, HttpStatus.OK);
        }
        List<BlogPostDTO> list = blogPostService.convertToBlogPostDTO(blogPostService.getAllBlogPosts());
        return new ResponseEntity<List<BlogPostDTO>>(list, HttpStatus.OK);

    }


    @GetMapping("/posts/{post_id}")
    public ResponseEntity<BlogPostDTO> getBlogPostById(@PathVariable("post_id") Integer postId) {
        BlogPost post = blogPostService.getBlogPostById(postId);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BlogPostDTO postDTO = blogPostService.convertToBlogPostDTO(post);
        return new ResponseEntity<BlogPostDTO>(postDTO, HttpStatus.OK);

    }


    @PutMapping("/posts")
    public ResponseEntity<BlogPost> updateBlogPost(@RequestBody BlogPostDTO blogPostDTO) {
        if (blogPostDTO == null||!blogPostService.isExists(blogPostDTO.getId()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getName();
        if(!blogPostService.isOwnPost(username, blogPostDTO.getId())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        BlogPost blogPost = blogPostService.convertToBlogPost(blogPostDTO);
        blogPost.setId(blogPostDTO.getId());
        blogPostService.updateBlogPost(blogPost);
        return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);
    }


    @PostMapping("/posts")
    public ResponseEntity<Void> addBlogPost(@RequestBody BlogPostDTO blogPostDTO) {
        BlogPost blogPost = blogPostService.convertToBlogPost(blogPostDTO);
        if (blogPostDTO == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        blogPostService.addBlogPost(blogPost);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/{post_id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable("post_id") Integer postId) {
        if (!blogPostService.isExists(postId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getName();
        if(!blogPostService.isOwnPost(username, postId)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        blogPostService.deleteBlogPost(postId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
