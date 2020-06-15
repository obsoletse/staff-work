package com.linbin.modules.message.service;

import com.linbin.modules.message.entity.MessageUser;
import com.linbin.system.service.IBaseService;

/**
 * @Author: LinBin
 * @Date: 2020/5/18 19:30
 * @Description:
 */
public interface MessageUserService extends IBaseService<MessageUser> {
    void updateStatus(Integer userId , Integer msgId,Integer Status);
    void handleAllRead(Integer userId);
    void handleAllDelete(Integer userId);
    void  handleDeleteAll(Integer userId);
}
