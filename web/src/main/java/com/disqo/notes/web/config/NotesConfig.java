package com.disqo.notes.web.config;

import com.disqo.notes.repository.UserRepository;
import com.disqo.notes.repository.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import javax.annotation.PostConstruct;
import java.time.LocalTime;

@Configuration
public class NotesConfig {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @PostConstruct
    void insertSingleUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("aa@mail.com");
        userEntity.setPassword(bCryptPasswordEncoder.encode("bbb"));
        userEntity.setCreateTime(LocalTime.now());
        userEntity.setLastUpdateTime(LocalTime.now());

        userRepository.save(userEntity);
    }
}