package com.uaa.auth.config.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .requestMatchers().antMatchers("/**")
//                .and().authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .anyRequest().authenticated()
//                .and().formLogin().permitAll()
//                .and().logout()
//                .logoutUrl("/logout")
//                .clearAuthentication(true)
//                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
//                .addLogoutHandler(customLogoutHandler());
//
//        //http.antMatcher("/api/**").addFilterAt(customSecurityFilter(), FilterSecurityInterceptor.class);
//
//    }

 /*   @Bean
    public CustomSecurityFilter customSecurityFilter() {
        return new CustomSecurityFilter();
    }
*/



    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }
}
