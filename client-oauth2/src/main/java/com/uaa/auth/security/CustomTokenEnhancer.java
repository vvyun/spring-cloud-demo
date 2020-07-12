package com.uaa.auth.security;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer extends JwtAccessTokenConverter implements Serializable {

    private static final String TOKEN_SEG_USER_ID = "TK-UserId";
    private static final String TOKEN_SEG_CLIENT = "TK-Client";
    private static final String TOKEN_SEG_ORGTYPE = "TK-OrgType";
    private static final String TOKEN_SEG_ORGID = "TK-OrgId";
    private static final String TOKEN_SEG_GRROUPID = "TK-GroupId";

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
                                     OAuth2Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        authentication.getUserAuthentication().getPrincipal();
        Map<String, Object> info = new HashMap<>();
        info.put(TOKEN_SEG_USER_ID, userDetails.getUserId());
        // token添加其他数据
        info.put(TOKEN_SEG_ORGTYPE, userDetails.getOrgType());
        info.put(TOKEN_SEG_ORGID, userDetails.getOrgId());
        info.put(TOKEN_SEG_GRROUPID, userDetails.getGroupType());

        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
        customAccessToken.setAdditionalInformation(info);

        OAuth2AccessToken enhancedToken = super.enhance(customAccessToken, authentication);
        enhancedToken.getAdditionalInformation().put(TOKEN_SEG_CLIENT, userDetails.getClientId());
        return enhancedToken;
    }

}
