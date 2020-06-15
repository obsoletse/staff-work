package com.linbin.modules.message.dao;

import com.linbin.modules.message.entity.Message;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/18 17:31
 * @Description:
 */
@Mapper
public interface MessageDao extends IBaseDao {
    List<Message> getMsgList(Integer index , Integer pageSize , String queryTitle , Integer queryStatus , Long queryStartDate , Long queryEndDate);
    Integer countTotal(String queryTitle ,Integer queryStatus , Long queryStartDate , Long queryEndDate);
    List<Message> queryUnreadMsg(Integer userId);
    List<Message> queryReadMsg(Integer userId);
    List<Message> queryRecycleMsg(Integer userId);
    Integer addPersonalMsg(Message message);
}
