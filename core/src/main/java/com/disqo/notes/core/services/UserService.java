package com.disqo.notes.core.services;

import java.util.Optional;

public interface UserService {

    Optional<String> getPassword(String email);
}