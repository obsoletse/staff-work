package com.linbin.modules.message.dao;

import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: LinBin
 * @Date: 2020/5/18 19:01
 * @Description:
 */
@Mapper
public interface MessageUserDao extends IBaseDao {
    void updateStatus(Integer userId , Integer msgId,Integer status);
    void handleAllRead(Integer userId);
    void handleAllDelete(Integer userId);
    void  handleDeleteAll(Integer userId);
}
