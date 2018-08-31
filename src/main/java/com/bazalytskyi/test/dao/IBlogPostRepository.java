package com.bazalytskyi.test.dao;

import java.util.List;


import com.bazalytskyi.test.data.entity.BlogPost;

public interface IBlogPostRepository {
	List<BlogPost> getAllBlogPosts();

	BlogPost getBlogPostById(int productId);

	void addBlogPost(BlogPost blogPost);

	void updateBlogPost(BlogPost blogPost);

    boolean isExists(int id);

    void deleteBlogPost(int productId);
	
}
