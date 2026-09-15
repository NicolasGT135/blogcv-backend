package com.nicolas.blogcv.controller;

import com.nicolas.blogcv.dto.SkillDTO;
import com.nicolas.blogcv.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = {"http://localhost:5173", "https://blogcv-frontend.vercel.app"})
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public List<SkillDTO> getAll() {
        return skillService.findAll();
    }

    @GetMapping("/{id}")
    public SkillDTO getById(@PathVariable Long id) {
        return skillService.findById(id);
    }

    @PostMapping
    public SkillDTO create(@Valid @RequestBody SkillDTO dto) {
        return skillService.create(dto);
    }

    @PutMapping("/{id}")
    public SkillDTO update(@PathVariable Long id, @Valid @RequestBody SkillDTO dto) {
        return skillService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        skillService.delete(id);
        return ResponseEntity.noContent().build();
    }
}