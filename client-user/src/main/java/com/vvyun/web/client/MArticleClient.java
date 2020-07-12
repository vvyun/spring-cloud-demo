package com.vvyun.web.client;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "client-common")
public interface MArticleClient {

    @RequestMapping(value = "/article/list-article", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    JSONObject listArticle();
}
