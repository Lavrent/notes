package com.disqo.notes.web.repository;

import com.disqo.notes.web.repository.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

    List<NoteEntity> findByUserEmail(String userEmail);
}