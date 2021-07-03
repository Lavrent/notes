package com.disqo.notes.core.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class NoteModel {
    private UUID uuid;
    private String title;
    private String note;
    private LocalTime createTime;
    private LocalTime lastUpdateTime;
}