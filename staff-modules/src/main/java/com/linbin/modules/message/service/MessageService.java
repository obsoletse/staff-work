package com.linbin.modules.message.service;

import com.linbin.modules.message.entity.Message;
import com.linbin.system.service.IBaseService;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/18 17:32
 * @Description:
 */
public interface MessageService extends IBaseService<Message> {
    List<Message> getMsgList(Integer page , Integer pageSize , String queryTitle ,Integer queryStatus , Long queryStartDate , Long queryEndDate);
    Integer countTotal(String queryTitle ,Integer queryStatus , Long queryStartDate , Long queryEndDate);
    List<Message> queryUnreadMsg(Integer userId);
    List<Message> queryReadMsg(Integer userId);
    List<Message> queryRecycleMsg(Integer userId);
    Integer addPersonalMsg(Message message);
}
