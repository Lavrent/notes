package com.disqo.notes.web.controllers;

import com.disqo.notes.web.dtos.NoteDto;
import com.disqo.notes.web.handlers.NoteHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteHandler noteHandler;

    @PostMapping
    public ResponseEntity<List<NoteDto>> createNotes(Principal principal, @RequestBody List<NoteDto> noteDtos) {
        String userEmail = principal.getName();

        return ResponseEntity.ok(noteHandler.createNotes(userEmail, noteDtos));
    }

    @GetMapping
    public ResponseEntity<List<NoteDto>> getNotes(Principal principal) {
        String userEmail = principal.getName();

        return ResponseEntity.ok(noteHandler.getNotes(userEmail));
    }
}