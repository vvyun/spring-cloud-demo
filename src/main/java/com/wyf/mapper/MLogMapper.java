package com.wyf.mapper;

import com.wyf.entity.MLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yunfei
 */
@Mapper
public interface MLogMapper {

    @Select("select uuid, type, request_content requestContent,response_content responseContent from Table_Log")
    List<MLog> getAllLogs();

    List<MLog> ListAllLog();

    List<MLog> findLogByType(int type);

    @Insert("insert into Table_Log(type,request_content,response_content) values(#{type},#{requestContent},#{responseContent})")
    int insert(MLog mLog);

    int insertLog(MLog mLog);

}
