package com.uaa.auth.dao;

import com.uaa.auth.entity.ClientSecret;
import com.uaa.auth.entity.MClientDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ClientSecretDAO {
    int create(ClientSecret clientSecret);

    List<ClientSecret> get(ClientSecret clientSecret);

    int update(ClientSecret clientSecret);

    MClientDetail getClientDetailsByClientId(String clientId);
}
