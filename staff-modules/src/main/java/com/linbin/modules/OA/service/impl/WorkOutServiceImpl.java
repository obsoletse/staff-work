package com.linbin.modules.OA.service.impl;

import com.linbin.modules.OA.dao.WorkOutDao;
import com.linbin.modules.OA.entity.WorkOutOrder;
import com.linbin.modules.OA.service.WorkOutService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/8 15:02
 * @Description:
 */
@Service
public class WorkOutServiceImpl extends IBaseServiceImpl<WorkOutOrder> implements WorkOutService {
    @Autowired
    private WorkOutDao workOutDao;

    @Override
    public List<WorkOutOrder> getMyWorkOutList(Integer page, Integer pageSize, String workNo, Long queryStartDate, Long queryEndDate) {
        return workOutDao.getMyWorkOutList((page - 1) * pageSize ,pageSize,workNo,queryStartDate,queryEndDate);
    }

    @Override
    public Integer countTotal(String workNo, Long queryStartDate, Long queryEndDate) {
        return workOutDao.countTotal(workNo,queryStartDate,queryEndDate);
    }

    @Override
    public List<WorkOutOrder> getWorkOutApprovalList(Integer page, Integer pageSize, Integer deptId, Long queryStartDate, Long queryEndDate) {
        return workOutDao.getWorkOutApprovalList((page-1)*pageSize,pageSize,deptId,queryStartDate,queryEndDate);
    }

    @Override
    public Integer countApprovalTotal(Integer deptId, Long queryStartDate, Long queryEndDate) {
        return  workOutDao.countApprovalTotal(deptId,queryStartDate,queryEndDate);
    }

    @Override
    public IBaseDao getBaseDao() {
        return workOutDao;
    }

    @Override
    public String getTableName() {
        return "work_outside";
    }

    @Override
    public Class<WorkOutOrder> getClassEntity() {
        return WorkOutOrder.class;
    }
}
