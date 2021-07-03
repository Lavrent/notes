package com.disqo.notes.web.repository.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "note")
public class NoteEntity {
    @Column(name = "title")
    private String title;
    @Column(name = "note")
    private String note;
    @Column(name = "create_time")
    private LocalTime createTime;
    @Column(name = "last_update_time")
    private LocalTime lastUpdateTime;
}
