package com.disqo.notes.web.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@RequiredArgsConstructor
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private static final String CLIENT_ID = "client";
    private static final String CLIENT_SECRET = "secret";
    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 100;
    private static final int REFRESH_TOKEN_VALIDITY_SECONDS = 100;
    private static final String RESOURCE_ID = "resource_id";
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final String[] authorizedGrantTypes = new String[]{"password", "authorization_code", "refresh_token"};
    private final TokenStore tokenStore;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(CLIENT_ID)
                .secret(passwordEncoder.encode(CLIENT_SECRET))
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS)
                .authorizedGrantTypes(authorizedGrantTypes)
                .scopes("read", "write")
                .resourceIds(RESOURCE_ID);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager);
    }
}