package com.linbin.modules.message.service.impl;

import com.linbin.modules.message.dao.MessageUserDao;
import com.linbin.modules.message.entity.MessageUser;
import com.linbin.modules.message.service.MessageUserService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: LinBin
 * @Date: 2020/5/18 19:30
 * @Description:
 */
@Service
public class MessageUserServiceImpl extends IBaseServiceImpl<MessageUser> implements MessageUserService {

    @Autowired
    private MessageUserDao messageUserDao;

    @Override
    public void updateStatus(Integer userId, Integer msgId,Integer status) {
        messageUserDao.updateStatus(userId,msgId,status);
    }

    @Override
    public void handleAllRead(Integer userId) {
        messageUserDao.handleAllRead(userId);
    }

    @Override
    public void handleAllDelete(Integer userId) {
        messageUserDao.handleAllDelete(userId);
    }

    @Override
    public void handleDeleteAll(Integer userId) {
        messageUserDao.handleDeleteAll(userId);
    }

    @Override
    public IBaseDao getBaseDao() {
        return messageUserDao;
    }

    @Override
    public String getTableName() {
        return "msg_user";
    }

    @Override
    public Class<MessageUser> getClassEntity() {
        return MessageUser.class;
    }
}
