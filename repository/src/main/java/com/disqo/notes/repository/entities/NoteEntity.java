package com.disqo.notes.repository.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "note")
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "title", length = 50)
    private String title;
    @Column(name = "note", length = 1000)
    private String note;
    @Column(name = "create_time")
    private LocalTime createTime;
    @Column(name = "last_update_time")
    private LocalTime lastUpdateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}