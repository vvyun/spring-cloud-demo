package com.uaa.auth.config;


import com.uaa.auth.rest.ClientSecretResource;
import com.uaa.auth.rest.SecurityResource;
import com.uaa.auth.rest.UserRoleResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;


@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(RequestContextFilter.class);
        register(ClientSecretResource.class);
        //配置restful package.
        register(SecurityResource.class);
        register(UserRoleResource.class);
    }
}
