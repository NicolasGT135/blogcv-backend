package com.nicolas.blogcv.controller;

import com.nicolas.blogcv.dto.PostDTO;
import com.nicolas.blogcv.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:5173")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<PostDTO> getPublished() {
        return postService.findAllPublished();
    }

    @GetMapping("/all")
    public List<PostDTO> getAll() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public PostDTO getById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @GetMapping("/slug/{slug}")
    public PostDTO getBySlug(@PathVariable String slug) {
        return postService.findBySlug(slug);
    }

    @PostMapping
    public PostDTO create(@Valid @RequestBody PostDTO dto,
                          @RequestParam(defaultValue = "1") Long userId) {
        return postService.create(dto, userId);
    }

    @PutMapping("/{id}")
    public PostDTO update(@PathVariable Long id, @Valid @RequestBody PostDTO dto) {
        return postService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}