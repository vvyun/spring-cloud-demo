package com.vvyun.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.vvyun.common.service.MArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/article/")
public class MArticleController {

    private final MArticleService mArticle;

    @Autowired
    public MArticleController(MArticleService mArticle) {
        this.mArticle = mArticle;
    }

    @RequestMapping(value = "list-article")
    public JSONObject listArticle() {
        return mArticle.listArticle();
    }

}
