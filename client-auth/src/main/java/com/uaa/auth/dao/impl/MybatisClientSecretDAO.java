package com.uaa.auth.dao.impl;

import com.uaa.auth.dao.ClientSecretDAO;
import com.uaa.auth.dao.mapper.ClientSecretMapper;
import com.uaa.auth.entity.ClientSecret;
import com.uaa.auth.entity.MClientDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MybatisClientSecretDAO implements ClientSecretDAO {

    @Autowired
    private ClientSecretMapper mapper;

    @Override
    public int create(ClientSecret clientSecret) {
        return mapper.insert(clientSecret);
    }

    @Override
    public List<ClientSecret> get(ClientSecret clientSecret) {
        Map<String, Object> params = new HashMap<>();
        params.put("clientId", clientSecret.getClientId());
        // return mapper.selectByParams(params);
        List<ClientSecret> list = new ArrayList<>();
        ClientSecret clientSecret1 = new ClientSecret();
        clientSecret1.setClientId("frontend");
        clientSecret1.setClientSecret("$e0801$65x9sjjnRPuKmqaFn3mICtPYnSWrjE7OB/pKzKTAI4ryhmVoa04cus+9sJcSAFKXZaJ8lcPO1I9H22TZk6EN4A==$o+ZWccaWXSA2t7TxE5VBRvz2W8psujU3RPPvejvNs4U=");
        list.add(clientSecret1);
        return list;
    }

    @Override
    public int update(ClientSecret clientSecret) {
        Map<String, Object> params = new HashMap<>();
        params.put("clientId", clientSecret.getClientId());
        params.put("clientSecret", clientSecret.getClientSecret());
        params.put("tenantId", clientSecret.getTenantId());
        params.put("purpose", clientSecret.getPurpose());
        params.put("status", clientSecret.getStatus().toString());

        return mapper.updateByParams(params);
    }

    @Override
    public MClientDetail getClientDetailsByClientId(String clientId) {
//        MClientDetail mClientDetail = new MClientDetail();
//        mClientDetail.setClientId("frontend");
//        mClientDetail.setClientSecret("$e0801$65x9sjjnRPuKmqaFn3mICtPYnSWrjE7OB/pKzKTAI4ryhmVoa04cus+9sJcSAFKXZaJ8lcPO1I9H22TZk6EN4A==$o+ZWccaWXSA2t7TxE5VBRvz2W8psujU3RPPvejvNs4U=");
//        mClientDetail.setPurpose("");
//        mClientDetail.setScopes("all");
//        mClientDetail.setGrantTypes("password,refresh_token,authorization_code");
//        mClientDetail.setAuthorities("");
//        mClientDetail.setRedirectUris("http://localhost:8080");

        return mapper.getClientDetailsByClientId(clientId);
    }
}
