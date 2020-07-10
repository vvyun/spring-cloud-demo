package com.uaa.auth.dao.mapper;

import com.uaa.auth.entity.ClientSecret;
import com.uaa.auth.entity.MClientDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface ClientSecretMapper {

    String getScope(@Param("clientId")String clientId,@Param("clientSecret")String clientSecret);


    int insert(ClientSecret record);

    List<ClientSecret> selectByParams(Map map);

    int updateByParams(Map map);

    int updateStatus(Map map);

    MClientDetail getClientDetailsByClientId(String clientId);
}
