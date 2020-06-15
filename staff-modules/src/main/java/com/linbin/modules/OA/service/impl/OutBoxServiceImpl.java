package com.linbin.modules.OA.service.impl;

import com.linbin.modules.OA.dao.OutBoxDao;
import com.linbin.modules.OA.entity.OutBox;
import com.linbin.modules.OA.service.OutBoxService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/11 18:07
 * @Description:
 */
@Service
public class OutBoxServiceImpl extends IBaseServiceImpl<OutBox> implements OutBoxService {

    @Autowired
    private OutBoxDao outBoxDao;


    @Override
    public Integer getSendBoxCount(Integer userId) {
        return outBoxDao.getSendBoxCount(userId);
    }

    @Override
    public Integer getDraftBoxCount(Integer userId) {
        return outBoxDao.getDraftBoxCount(userId);
    }

    @Override
    public List<OutBox> getSendBoxList(Integer page, Integer pageSize, Integer userId) {
        return outBoxDao.getSendBoxList((page - 1) * pageSize,pageSize,userId);
    }

    @Override
    public List<OutBox> getDraftBoxList(Integer page, Integer pageSize, Integer userId) {
        return outBoxDao.getDraftBoxList((page - 1) * pageSize,pageSize,userId);
    }

    @Override
    public IBaseDao getBaseDao() {
        return outBoxDao;
    }

    @Override
    public String getTableName() {
        return "email_send";
    }

    @Override
    public Class<OutBox> getClassEntity() {
        return OutBox.class;
    }
}
