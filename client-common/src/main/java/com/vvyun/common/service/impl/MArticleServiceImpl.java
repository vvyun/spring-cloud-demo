package com.vvyun.common.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vvyun.common.service.MArticleService;
import org.springframework.stereotype.Service;

@Service(value = "MArticle")
public class MArticleServiceImpl implements MArticleService {

    @Override
    public JSONObject listArticle() {

        JSONObject item = new JSONObject() {
            {
                put("id", "0001");
                put("title", "good good studay");
                put("content", "good good studay, day day up!");
            }
        };

        return new JSONObject() {
            {
                put("total", "1");
                put("items", new JSONArray() {{
                    add(item);
                }});
            }
        };
    }
}
