package com.linbin.modules.message.service.impl;

import com.linbin.modules.message.dao.MessageDao;
import com.linbin.modules.message.entity.Message;
import com.linbin.modules.message.service.MessageService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/18 17:32
 * @Description:
 */
@Service
public class MessageServiceImpl extends IBaseServiceImpl<Message> implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public List<Message> getMsgList(Integer page, Integer pageSize, String queryTitle, Integer queryStatus, Long queryStartDate, Long queryEndDate) {
        if (queryTitle != null){
            queryTitle = "%" + queryTitle + "%";
        }
        return messageDao.getMsgList((page-1)*pageSize,pageSize,queryTitle,queryStatus,queryStartDate,queryEndDate);
    }

    @Override
    public Integer countTotal(String queryTitle, Integer queryStatus, Long queryStartDate, Long queryEndDate) {
        if (queryTitle != null){
            queryTitle = "%" + queryTitle + "%";
        }
        return messageDao.countTotal(queryTitle,queryStatus,queryStartDate,queryEndDate);
    }

    @Override
    public List<Message> queryUnreadMsg(Integer userId) {
        return messageDao.queryUnreadMsg(userId);
    }

    @Override
    public List<Message> queryReadMsg(Integer userId) {
        return messageDao.queryReadMsg(userId);
    }

    @Override
    public List<Message> queryRecycleMsg(Integer userId) {
        return messageDao.queryRecycleMsg(userId);
    }

    @Override
    public Integer addPersonalMsg(Message message) {
        return messageDao.addPersonalMsg(message);
    }

    @Override
    public IBaseDao getBaseDao() {
        return messageDao;
    }

    @Override
    public String getTableName() {
        return "message";
    }

    @Override
    public Class<Message> getClassEntity() {
        return Message.class;
    }
}
