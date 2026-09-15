package com.nicolas.blogcv.service;

import com.nicolas.blogcv.dto.SkillDTO;
import com.nicolas.blogcv.entity.Skill;
import com.nicolas.blogcv.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<SkillDTO> findAll() {
        return skillRepository.findAllByOrderByDisplayOrderAsc().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public SkillDTO findById(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill no encontrada"));
        return toDTO(skill);
    }

    public SkillDTO create(SkillDTO dto) {
        Skill skill = toEntity(dto);
        return toDTO(skillRepository.save(skill));
    }

    public SkillDTO update(Long id, SkillDTO dto) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill no encontrada"));

        skill.setName(dto.getName());
        skill.setCategory(dto.getCategory());
        skill.setLevel(dto.getLevel());
        skill.setIconUrl(dto.getIconUrl());
        skill.setDisplayOrder(dto.getDisplayOrder());

        return toDTO(skillRepository.save(skill));
    }

    public void delete(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new RuntimeException("Skill no encontrada");
        }
        skillRepository.deleteById(id);
    }

    private SkillDTO toDTO(Skill s) {
        return new SkillDTO(s.getId(), s.getName(), s.getCategory(),
                s.getLevel(), s.getIconUrl(), s.getDisplayOrder());
    }

    private Skill toEntity(SkillDTO dto) {
        Skill s = new Skill();
        s.setName(dto.getName());
        s.setCategory(dto.getCategory());
        s.setLevel(dto.getLevel());
        s.setIconUrl(dto.getIconUrl());
        s.setDisplayOrder(dto.getDisplayOrder());
        return s;
    }
}