package com.disqo.notes.web.repository.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user_table")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "create_time")
    private LocalTime createTime;
    @Column(name = "last_update_time")
    private LocalTime lastUpdateTime;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<NoteEntity> notes = new HashSet<>();
}
