package com.nicolas.blogcv.service;

import com.nicolas.blogcv.dto.PostDTO;
import com.nicolas.blogcv.entity.Category;
import com.nicolas.blogcv.entity.Post;
import com.nicolas.blogcv.entity.User;
import com.nicolas.blogcv.repository.CategoryRepository;
import com.nicolas.blogcv.repository.PostRepository;
import com.nicolas.blogcv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<PostDTO> findAllPublished() {
        return postRepository.findByPublishedTrueOrderByPublishedAtDesc().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<PostDTO> findAll() {
        return postRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PostDTO findBySlug(String slug) {
        Post post = postRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Post no encontrado"));
        return toDTO(post);
    }

    public PostDTO findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post no encontrado"));
        return toDTO(post);
    }

    public PostDTO create(PostDTO dto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setSlug(dto.getSlug());
        post.setExcerpt(dto.getExcerpt());
        post.setContent(dto.getContent());
        post.setCoverImageUrl(dto.getCoverImageUrl());
        post.setPublished(dto.getPublished() != null ? dto.getPublished() : false);
        post.setUser(user);

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            post.setCategory(category);
        }

        if (Boolean.TRUE.equals(post.getPublished())) {
            post.setPublishedAt(LocalDateTime.now());
        }

        return toDTO(postRepository.save(post));
    }

    public PostDTO update(Long id, PostDTO dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post no encontrado"));

        post.setTitle(dto.getTitle());
        post.setSlug(dto.getSlug());
        post.setExcerpt(dto.getExcerpt());
        post.setContent(dto.getContent());
        post.setCoverImageUrl(dto.getCoverImageUrl());

        boolean wasPublished = Boolean.TRUE.equals(post.getPublished());
        boolean nowPublished = Boolean.TRUE.equals(dto.getPublished());

        post.setPublished(nowPublished);

        if (!wasPublished && nowPublished) {
            post.setPublishedAt(LocalDateTime.now());
        }

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            post.setCategory(category);
        }

        return toDTO(postRepository.save(post));
    }

    public void delete(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post no encontrado");
        }
        postRepository.deleteById(id);
    }

    private PostDTO toDTO(Post p) {
        PostDTO dto = new PostDTO();
        dto.setId(p.getId());
        dto.setTitle(p.getTitle());
        dto.setSlug(p.getSlug());
        dto.setExcerpt(p.getExcerpt());
        dto.setContent(p.getContent());
        dto.setCoverImageUrl(p.getCoverImageUrl());
        dto.setPublished(p.getPublished());
        if (p.getCategory() != null) {
            dto.setCategoryId(p.getCategory().getId());
            dto.setCategoryName(p.getCategory().getName());
        }
        if (p.getUser() != null) {
            dto.setAuthorName(p.getUser().getFullName());
        }
        dto.setCreatedAt(p.getCreatedAt());
        dto.setUpdatedAt(p.getUpdatedAt());
        dto.setPublishedAt(p.getPublishedAt());
        return dto;
    }
}