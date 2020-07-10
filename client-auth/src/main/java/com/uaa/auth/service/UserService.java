package com.uaa.auth.service;

import com.uaa.auth.dao.UserAccessDAO;
import com.uaa.auth.entity.XtUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户数据服务
 * @author wyf
 */
@Service
public class UserService {

    @Resource(name = "UserAccessDAO")
    private UserAccessDAO userAccessDAO;

    public XtUser checkUsernameAndPassword(Map<String, Object> params) {
        //checkUsernameAndPassword
//        Map<String, Object> ret = new HashMap<>();
//        ret.put("userId", UUID.randomUUID().toString());
        XtUser userMap = userAccessDAO.findUserByUserName(params);
        return userMap;
    }
}
