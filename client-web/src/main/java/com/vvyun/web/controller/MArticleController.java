package com.vvyun.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.vvyun.web.client.MArticleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/article/")
public class MArticleController {

    private MArticleClient mArticleClient;

    @Autowired
    public MArticleController(MArticleClient mArticleClient) {
        this.mArticleClient = mArticleClient;
    }

    @RequestMapping(value = "list-article")
    public JSONObject listArticle() {
        return mArticleClient.listArticle();
    }

}
