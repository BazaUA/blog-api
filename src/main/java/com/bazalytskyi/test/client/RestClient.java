package com.bazalytskyi.test.client;

import com.bazalytskyi.test.data.dto.BlogPostDTO;
import com.bazalytskyi.test.data.entity.Publisher;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient {
    private String url = "http://localhost:8080/api/posts";

    public void getPostById(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = url + "/{id_post}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<BlogPostDTO> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.GET, requestEntity,
                BlogPostDTO.class, id);
        BlogPostDTO post = responseEntity.getBody();
        System.out.println("Title:" + post.getTitle() + ", Publisher:" + post.getPublisher());
    }

    public void getAllPosts() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = url;
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<BlogPostDTO[]> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.GET, requestEntity,
                BlogPostDTO[].class);
        BlogPostDTO[] posts = responseEntity.getBody();
        for (BlogPostDTO post : posts) {
            System.out.println("Title:" + post.getTitle() + ", Publisher:" + post.getPublisher());
        }
    }

    public void addPost(int publisherId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = url;
        BlogPostDTO blogPostDTO = new BlogPostDTO();
        blogPostDTO.setPublisherId(publisherId);
        blogPostDTO.setTitle("Test Title (3)");
        blogPostDTO.setContent("Test Content (3)");
        HttpEntity<BlogPostDTO> requestEntity = new HttpEntity<BlogPostDTO>(blogPostDTO, headers);
        restTemplate.postForLocation(requestUrl, requestEntity);
    }

    public void updatePost(int postId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = url;
        BlogPostDTO post = new BlogPostDTO();
        post.setId(postId);
        post.setTitle("Test update title");
        post.setContent("Test update content");
        HttpEntity<BlogPostDTO> requestEntity = new HttpEntity<BlogPostDTO>(post, headers);
        restTemplate.put(requestUrl, requestEntity);
    }

    public void deletePost(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = url + "/" + id;
        restTemplate.delete(requestUrl);
    }

    public static void main(String args[]) {
        RestClient client = new RestClient();
        //  client.addPost(2);
        // client.updatePost(112);
        // client.getAllPosts();
        // client.getPostById(92);
        // client.deletePost(92);
    }
}