package com.bazalytskyi.test.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.bazalytskyi.test.data.entity.BlogPost;
import com.bazalytskyi.test.data.metamodel.BlogPost_;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class BlogPostRepository implements IBlogPostRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BlogPost> getAllBlogPosts() {
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BlogPost> cq = qb.createQuery(BlogPost.class);
        Root<BlogPost> rootEntry = cq.from(BlogPost.class);
        CriteriaQuery<BlogPost> all = cq.select(rootEntry);
        TypedQuery<BlogPost> allQuery = entityManager.createQuery(all);
        List<BlogPost> result = allQuery.getResultList();
        return result;
    }

    @Override
    public BlogPost getBlogPostById(int postId) {
        return entityManager.find(BlogPost.class, postId);
    }

    @Override
    public void addBlogPost(BlogPost blogPost) {
        entityManager.persist(blogPost);
    }

    @Override
    public void updateBlogPost(BlogPost blogPost) {
        BlogPost newBlogPost = this.getBlogPostById(blogPost.getId());
        newBlogPost.setTitle(blogPost.getTitle());
        newBlogPost.setContent(blogPost.getContent());
        entityManager.flush();
    }

    @Override
    public boolean isExists(int id) {
        return entityManager.find(BlogPost.class, id) == null ? false : true;
    }

    @Override
    public void deleteBlogPost(int productId) {
        entityManager.remove(this.getBlogPostById(productId));
    }

}
