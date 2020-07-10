package com.uaa.auth.service;

import com.uaa.auth.dao.ClientSecretDAO;
import com.uaa.auth.dto.ApiClientDTO;
import com.uaa.auth.entity.ClientSecret;
import com.uaa.auth.entity.ClientSecretStatus;
import com.uaa.auth.entity.MClientDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wyf
 * @date 2017/10/18
 */
@Service
public class ClientSecretService {
    @Autowired
    private ClientSecretDAO clientSecretDao;

    public void createClientSecret(ApiClientDTO apiClient) {
        ClientSecret clientSecret = new ClientSecret.ClientSecretBuilder()
                .withClientId(apiClient.getClientId())
                .withClientSecret(apiClient.getClientSecret())
                .withTenantId(apiClient.getTenantId())
                .withPurpose(apiClient.getPurpose())
                .withStatus(ClientSecretStatus.ACTIVE)
                .build();
        clientSecretDao.create(clientSecret);
    }

    public ApiClientDTO getClientSecretByClientId(String clientId) {
        ClientSecret clientSecret = new ClientSecret.ClientSecretBuilder()
                .withClientId(clientId)
                .build();
        List<ClientSecret> results = clientSecretDao.get(clientSecret);
        System.out.println(results.size());
        if (results.size() >= 1) {
            return convert(results.get(0));
        }
        return null;
    }

    public int updateClientSecret(ApiClientDTO apiClient) {
        ClientSecret clientSecret = new ClientSecret.ClientSecretBuilder()
                .withClientSecret(apiClient.getClientSecret())
                .withPurpose(apiClient.getPurpose())
                .withStatus(ClientSecretStatus.valueOf(apiClient.getStatus()))
                .withClientId(apiClient.getClientId())
                .withTenantId(apiClient.getTenantId())
                .build();
        return clientSecretDao.update(clientSecret);
    }

    private ApiClientDTO convert(ClientSecret clientSecret) {
        ApiClientDTO apiClient = new ApiClientDTO();
        apiClient.setClientId(clientSecret.getClientId());
        apiClient.setClientSecret(clientSecret.getClientSecret());

        apiClient.setStatus(clientSecret.getStatus().name());
/*        apiClient.setStatus(clientSecret.getStatus().toString());
        apiClient.setPurpose(clientSecret.getPurpose());
        apiClient.setUserId(clientSecret.getUserId());*/
        return apiClient;
    }

    public MClientDetail getClientDetailsByClientId(String clientId) {
        return clientSecretDao.getClientDetailsByClientId(clientId);
    }
}
