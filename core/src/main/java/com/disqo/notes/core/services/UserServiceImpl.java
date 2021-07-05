package com.disqo.notes.core.services;

import com.disqo.notes.repository.UserRepository;
import com.disqo.notes.repository.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<String> getPassword(String email) {
        return userRepository.findByEmail(email)
                .map(UserEntity::getPassword);
    }
}