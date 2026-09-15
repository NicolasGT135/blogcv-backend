package com.nicolas.blogcv.repository;

import com.nicolas.blogcv.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findBySlug(String slug);

    List<Post> findByPublishedTrueOrderByPublishedAtDesc();

    List<Post> findByCategoryIdAndPublishedTrue(Long categoryId);

    List<Post> findByUserId(Long userId);
}