package com.disqo.notes.repository;

import com.disqo.notes.repository.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

    List<NoteEntity> findAllByUserEmail(String userEmail);

    Optional<NoteEntity> findByUuidAndUserEmail(UUID uuid, String userEmail);
}