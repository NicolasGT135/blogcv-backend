package com.nicolas.blogcv.repository;

import com.nicolas.blogcv.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByFeaturedTrueOrderByCreatedAtDesc();

    List<Project> findByUserIdOrderByCreatedAtDesc(Long userId);
}
