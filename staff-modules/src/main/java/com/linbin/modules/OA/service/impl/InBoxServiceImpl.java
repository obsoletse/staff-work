package com.linbin.modules.OA.service.impl;

import com.linbin.modules.OA.dao.InBoxDao;
import com.linbin.modules.OA.entity.InBox;
import com.linbin.modules.OA.service.InBoxService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/11 18:06
 * @Description:
 */
@Service
public class InBoxServiceImpl extends IBaseServiceImpl<InBox> implements InBoxService {

    @Autowired
    private InBoxDao inBoxDao;

    @Override
    public Integer getReceiveBoxCount(Integer userId) {
        return inBoxDao.getReceiveBoxCount(userId);
    }

    @Override
    public Integer getBinBoxCount(Integer userId) {
        return inBoxDao.getBinBoxCount(userId);
    }

    @Override
    public List<InBox> getReceiveBoxList(Integer page, Integer pageSize, Integer userId) {
        return inBoxDao.getReceiveBoxList((page - 1) * pageSize , pageSize ,userId);
    }

    @Override
    public List<InBox> getBinBoxList(Integer page, Integer pageSize, Integer userId) {
        return inBoxDao.getBinBoxList((page - 1) * pageSize ,pageSize,userId);
    }

    @Override
    public void updateReceiveStatus(Integer status , Integer id) {
        inBoxDao.updateReceiveStatus(status, id);
    }

    @Override
    public void updateEmailStatus(Integer id) {
        inBoxDao.updateEmailStatus(id);
    }

    @Override
    public IBaseDao getBaseDao() {
        return inBoxDao;
    }

    @Override
    public String getTableName() {
        return "email_receive";
    }

    @Override
    public Class<InBox> getClassEntity() {
        return InBox.class;
    }
}
