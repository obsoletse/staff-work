package com.linbin.modules.attendance.service.impl;

import com.linbin.modules.attendance.dao.CardReplaceDao;
import com.linbin.modules.attendance.entity.CardReplace;
import com.linbin.modules.attendance.service.CardReplaceService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/14 14:41
 * @Description:
 */
@Service
public class CardReplaceServiceImpl extends IBaseServiceImpl<CardReplace> implements CardReplaceService {

    @Autowired
    private CardReplaceDao cardReplaceDao;

    @Override
    public CardReplace getOneApprovalInfo(String username, Long clockTime) {
        return cardReplaceDao.getOneApprovalInfo(username,clockTime);
    }

    @Override
    public List<CardReplace> getCardReplaceList(Integer page, Integer pageSize, Integer deptId, String queryType, String queryStatus, String queryApprovalStatus) {
        return cardReplaceDao.getCardReplaceList((page-1)*pageSize,pageSize,deptId,queryType,queryStatus,queryApprovalStatus);
    }

    @Override
    public Integer countTotal(Integer deptId, String queryType, String queryStatus, String queryApprovalStatus) {
        return cardReplaceDao.countTotal(deptId,queryType,queryStatus,queryApprovalStatus);
    }

    @Override
    public IBaseDao getBaseDao() {
        return cardReplaceDao;
    }

    @Override
    public String getTableName() {
        return "card_replace";
    }

    @Override
    public Class<CardReplace> getClassEntity() {
        return CardReplace.class;
    }
}
