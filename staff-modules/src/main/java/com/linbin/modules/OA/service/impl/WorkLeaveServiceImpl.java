package com.linbin.modules.OA.service.impl;

import com.linbin.modules.OA.dao.WorkLeaveDao;
import com.linbin.modules.OA.entity.WorkLeaveOrder;
import com.linbin.modules.OA.service.WorkLeaveService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/7 21:31
 * @Description:
 */
@Service
public class WorkLeaveServiceImpl extends IBaseServiceImpl<WorkLeaveOrder> implements WorkLeaveService {

    @Autowired
    private WorkLeaveDao workLeaveDao;


    @Override
    public List<WorkLeaveOrder> getMyWorkLeaveList(Integer page, Integer pageSize, String workNo, Long queryStartDate, Long queryEndDate) {
        return workLeaveDao.getMyWorkLeaveList((page-1)*pageSize,pageSize,workNo,queryStartDate,queryEndDate);
    }

    @Override
    public Integer countTotal(String workNo, Long queryStartDate, Long queryEndDate) {
        return workLeaveDao.countTotal(workNo,queryStartDate,queryEndDate);
    }

    @Override
    public List<WorkLeaveOrder> getWorkLeaveApprovalList(Integer page, Integer pageSize, Integer deptId, Long queryStartDate, Long queryEndDate) {
        return workLeaveDao.getWorkLeaveApprovalList((page-1)*pageSize,pageSize,deptId,queryStartDate,queryEndDate);
    }

    @Override
    public Integer countApprovalTotal(Integer deptId, Long queryStartDate, Long queryEndDate) {
        return  workLeaveDao.countApprovalTotal(deptId,queryStartDate,queryEndDate);
    }

    @Override
    public IBaseDao getBaseDao() {
        return workLeaveDao;
    }

    @Override
    public String getTableName() {
        return "work_leave";
    }

    @Override
    public Class<WorkLeaveOrder> getClassEntity() {
        return WorkLeaveOrder.class;
    }
}
