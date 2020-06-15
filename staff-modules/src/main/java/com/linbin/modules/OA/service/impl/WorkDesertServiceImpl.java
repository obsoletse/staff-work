package com.linbin.modules.OA.service.impl;

import com.linbin.modules.OA.dao.WorkDesertDao;
import com.linbin.modules.OA.entity.WorkDesertOrder;
import com.linbin.modules.OA.service.WorkDesertService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/8 17:45
 * @Description:
 */
@Service
public class WorkDesertServiceImpl extends IBaseServiceImpl<WorkDesertOrder> implements WorkDesertService {

    @Autowired
    private WorkDesertDao workDesertDao;


    @Override
    public List<WorkDesertOrder> getMyWorkDesertList(Integer page, Integer pageSize, String workNo, Long queryStartDate, Long queryEndDate) {
        return workDesertDao.getMyWorkDesertList((page-1)*pageSize,pageSize,workNo,queryStartDate,queryEndDate);
    }

    @Override
    public Integer countTotal(String workNo, Long queryStartDate, Long queryEndDate) {
        return workDesertDao.countTotal(workNo,queryStartDate,queryEndDate);
    }

    @Override
    public List<WorkDesertOrder> getWorkDesertApprovalList(Integer page, Integer pageSize, Integer deptId, Long queryStartDate, Long queryEndDate) {
        return workDesertDao.getWorkDesertApprovalList((page-1)*pageSize,pageSize,deptId,queryStartDate,queryEndDate);
    }

    @Override
    public Integer countApprovalTotal(Integer deptId, Long queryStartDate, Long queryEndDate) {
        return  workDesertDao.countApprovalTotal(deptId,queryStartDate,queryEndDate);
    }
    
    @Override
    public IBaseDao getBaseDao() {
        return workDesertDao;
    }

    @Override
    public String getTableName() {
        return "work_desert";
    }

    @Override
    public Class<WorkDesertOrder> getClassEntity() {
        return WorkDesertOrder.class;
    }
}
