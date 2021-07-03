package com.disqo.notes.web.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteDto {
    @JsonProperty(required = true)
    private String title;
    private String note;
    private LocalTime createTime;
    private LocalTime lastUpdateTime;
}
