package com.nicolas.blogcv.service;

import com.nicolas.blogcv.dto.ContactMessageDTO;
import com.nicolas.blogcv.entity.ContactMessage;
import com.nicolas.blogcv.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactMessageService {

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    public List<ContactMessageDTO> findAll() {
        return contactMessageRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<ContactMessageDTO> findUnread() {
        return contactMessageRepository.findByIsReadFalseOrderByCreatedAtDesc().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ContactMessageDTO create(ContactMessageDTO dto) {
        ContactMessage message = new ContactMessage();
        message.setName(dto.getName());
        message.setEmail(dto.getEmail());
        message.setSubject(dto.getSubject());
        message.setMessage(dto.getMessage());
        message.setIsRead(false);
        return toDTO(contactMessageRepository.save(message));
    }

    public ContactMessageDTO markAsRead(Long id) {
        ContactMessage message = contactMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));
        message.setIsRead(true);
        return toDTO(contactMessageRepository.save(message));
    }

    public void delete(Long id) {
        if (!contactMessageRepository.existsById(id)) {
            throw new RuntimeException("Mensaje no encontrado");
        }
        contactMessageRepository.deleteById(id);
    }

    private ContactMessageDTO toDTO(ContactMessage m) {
        ContactMessageDTO dto = new ContactMessageDTO();
        dto.setId(m.getId());
        dto.setName(m.getName());
        dto.setEmail(m.getEmail());
        dto.setSubject(m.getSubject());
        dto.setMessage(m.getMessage());
        dto.setIsRead(m.getIsRead());
        dto.setCreatedAt(m.getCreatedAt());
        return dto;
    }
}