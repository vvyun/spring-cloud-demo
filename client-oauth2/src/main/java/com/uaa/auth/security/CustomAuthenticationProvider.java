package com.uaa.auth.security;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.uaa.auth.entity.UserAccess;
import com.uaa.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wyf
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService userservice;

    @Autowired
    public CustomAuthenticationProvider(UserService userservice) {
        this.userservice = userservice;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        Map data;
        if (authentication.getDetails() instanceof Map) {
            data = (Map) authentication.getDetails();
        } else {
            return null;
        }
        String clientId = (String) data.get("client");
        String type = (String) data.get("type");
        if (clientId == null) {
            clientId = "unknown_client";
        }

        String password = (String) authentication.getCredentials();

        UserAccess map = userservice.checkUsernameAndPassword(username, password, type);

        String userId = map.getUserId();
        if (StringUtils.isBlank(userId)) {
            String errorCode = map.getFlag();
            throw new BadCredentialsException(errorCode);
        }

        CustomUserDetails customUserDetails = buildCustomUserDetails(username, password, userId, clientId);

        // token添加用户其他信息
        customUserDetails.setOrgId(map.getOrgId());
        customUserDetails.setOrgType(map.getOrgType());
        customUserDetails.setGroupType(map.getGroupId());

        return new CustomAuthenticationToken(customUserDetails);
    }

    private CustomUserDetails buildCustomUserDetails(String username, String password, String userId, String clientId) {
        return new CustomUserDetails.CustomUserDetailsBuilder()
                .withUserId(userId)
                .withPassword(password)
                .withUsername(username)
                .withClientId(clientId)
                .build();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
