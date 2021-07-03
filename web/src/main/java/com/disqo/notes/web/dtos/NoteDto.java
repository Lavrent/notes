package com.disqo.notes.web.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteDto {
    private UUID id;
    private String title;
    private String note;
    private LocalTime createTime;
    private LocalTime lastUpdateTime;
}
