package com.vvyun.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yunfei
 * demo
 */
@RestController
public class DemoController {

    @RequestMapping("/demo")
    public Map<String, Object> listAll() {

        List<Map<String, Object>> items = new ArrayList<>();

        for (int i = 1; i < 100; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", i);
            item.put("name", "S" + i);
            item.put("status", "可用");
            items.add(item);
        }

        int count = items.size();

        items = items.subList(0, 20);

        Map<String, Object> data = new HashMap<>();
        data.put("total", count);
        data.put("items", items);

        Map<String, Object> res = new HashMap<>();
        res.put("code", 20000);
        res.put("data", data);

        return res;
    }

}
