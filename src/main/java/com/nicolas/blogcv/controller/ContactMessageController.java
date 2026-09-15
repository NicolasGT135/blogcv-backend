package com.nicolas.blogcv.controller;

import com.nicolas.blogcv.dto.ContactMessageDTO;
import com.nicolas.blogcv.service.ContactMessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = {"http://localhost:5173", "https://blogcv-frontend.vercel.app"})
public class ContactMessageController {

    @Autowired
    private ContactMessageService contactMessageService;

    @GetMapping
    public List<ContactMessageDTO> getAll() {
        return contactMessageService.findAll();
    }

    @GetMapping("/unread")
    public List<ContactMessageDTO> getUnread() {
        return contactMessageService.findUnread();
    }

    @PostMapping
    public ContactMessageDTO create(@Valid @RequestBody ContactMessageDTO dto) {
        return contactMessageService.create(dto);
    }

    @PutMapping("/{id}/read")
    public ContactMessageDTO markAsRead(@PathVariable Long id) {
        return contactMessageService.markAsRead(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contactMessageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}