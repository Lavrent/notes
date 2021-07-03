package com.disqo.notes.core.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class UserModel {
    private String email;
    private String password;
    private LocalTime createTime;
    private LocalTime lastUpdateTime;
}