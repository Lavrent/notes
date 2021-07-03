package com.disqo.notes.web.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class NoteDto {
    private String title;
    private String note;
    private LocalTime createTime;
    private LocalTime lastUpdateTime;
}
