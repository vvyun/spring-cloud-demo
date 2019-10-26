package com.wyf.service;

import com.wyf.entity.MLog;
import com.wyf.mapper.MLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MLogService {

    @Autowired
    MLogMapper MLogMapper;

    public void add(MLog newLog) {
//        tableLogMapper.insert(newLog);
        MLogMapper.insertLog(newLog);
    }

    public List<MLog> listAllLog(){
        return MLogMapper.ListAllLog();
    }
}
