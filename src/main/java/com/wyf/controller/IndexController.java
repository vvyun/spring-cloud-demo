package com.wyf.controller;

import com.wyf.entity.MLog;
import com.wyf.service.MLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Yunfei
 */
@Controller
public class IndexController {

    @Autowired
    MLogService logService;

    @RequestMapping("/")
    public String indexPage() {

//        for (int i = 0; i < 2 ; i++) {
//            MLog newLog = new MLog();
//            newLog.setFlag(1);
//            newLog.setType(1);
//            newLog.setRequestContent("{id:"+i+"}");
//            newLog.setResponseContent("{name:wyf"+i+"}");
//            logService.add(newLog);
//        }

//        List list = logService.getAllLogs();
//        List list = logService.listAllLog();
//
//        for (Object t : list) {
//            MLog log = (MLog) t;
//            System.out.println(log.toString());
//        }

        return "index";
    }

}
