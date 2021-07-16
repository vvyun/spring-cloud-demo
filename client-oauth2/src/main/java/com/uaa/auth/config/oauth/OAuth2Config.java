package com.uaa.auth.config.oauth;


import com.uaa.auth.security.CustomAuthorizationTokenServices;
import com.uaa.auth.security.CustomRedisTokenStore;
import com.uaa.auth.security.CustomTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {


    private AuthenticationManager authenticationManager;

    private WebResponseExceptionTranslator webResponseExceptionTranslator;

    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    public OAuth2Config(AuthenticationManager authenticationManager, WebResponseExceptionTranslator webResponseExceptionTranslator,
                        RedisConnectionFactory redisConnectionFactory) {
        this.authenticationManager = authenticationManager;
        this.webResponseExceptionTranslator = webResponseExceptionTranslator;
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Bean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
//        return new CustomRedisTokenStore(redisConnectionFactory);
        return new InMemoryTokenStore();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(myClientDetailsService());
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                // TokenStore的实现类，有InMemoryTokenStore、JdbcTokenStore、JwkTokenStore、RedisTokenStore。
                .tokenStore(tokenStore(redisConnectionFactory))         // token缓存
                .tokenServices(authorizationServerTokenServices())
                .accessTokenConverter(accessTokenConverter())           // token转化
                .exceptionTranslator(webResponseExceptionTranslator);   // 异常处理
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new CustomTokenEnhancer();
        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("token.jks"), "123456".toCharArray())
                .getKeyPair("jwt");
        converter.setKeyPair(keyPair);
//        converter.setSigningKey("secret");
        return converter;
    }

    @Bean
    public AuthorizationServerTokenServices authorizationServerTokenServices() {
        CustomAuthorizationTokenServices customTokenServices = new CustomAuthorizationTokenServices();
        customTokenServices.setTokenStore(tokenStore(redisConnectionFactory));
        customTokenServices.setSupportRefreshToken(true);
        customTokenServices.setReuseRefreshToken(false);
        customTokenServices.setClientDetailsService(myClientDetailsService());
        customTokenServices.setTokenEnhancer(accessTokenConverter());
        return customTokenServices;
    }

    @Configuration
    protected static class RedisConfiguration {

        @Bean
        @ConditionalOnMissingBean(name = "redisTemplate")
        public RedisTemplate<Object, Object> redisTemplate(
                RedisConnectionFactory redisConnectionFactory) {
            RedisTemplate<Object, Object> template = new RedisTemplate<>();
            template.setConnectionFactory(redisConnectionFactory);
            return template;
        }

        @Bean
        @ConditionalOnMissingBean(StringRedisTemplate.class)
        public StringRedisTemplate stringRedisTemplate(
                RedisConnectionFactory redisConnectionFactory) {
            StringRedisTemplate template = new StringRedisTemplate();
            template.setConnectionFactory(redisConnectionFactory);
            return template;
        }
    }

    @Bean
    public ClientDetailsService myClientDetailsService() {
        return clientId -> {

            String resourceIds = "";
            String scopes = "all";
            String grantTypes = "password,refresh_token,authorization_code";
            String redirectUris = "http://localhost:8080";

            BaseClientDetails details = new BaseClientDetails("frontend", resourceIds, scopes, grantTypes,
                    null, redirectUris);
            details.setClientSecret("$e0801$65x9sjjnRPuKmqaFn3mICtPYnSWrjE7OB/pKzKTAI4ryhmVoa04cus+9sJcSAFKXZaJ8lcPO1I9" +
                    "H22TZk6EN4A==$o+ZWccaWXSA2t7TxE5VBRvz2W8psujU3RPPvejvNs4U=");

            return details;
        };
    }


}
