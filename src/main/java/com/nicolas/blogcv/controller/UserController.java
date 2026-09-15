package com.nicolas.blogcv.controller;

import com.nicolas.blogcv.dto.UserDTO;
import com.nicolas.blogcv.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:5173", "https://blogcv-frontend.vercel.app"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/username/{username}")
    public UserDTO getByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
        return userService.update(id, dto);
    }
}