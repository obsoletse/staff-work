package com.linbin.modules.OA.service.impl;

import com.linbin.modules.OA.dao.WorkOverDao;
import com.linbin.modules.OA.entity.WorkOverOrder;
import com.linbin.modules.OA.service.WorkOverService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/8 16:59
 * @Description:
 */
@Service
public class WorkOverServiceImpl extends IBaseServiceImpl<WorkOverOrder> implements WorkOverService {

    @Autowired
    private WorkOverDao workOverDao;

    @Override
    public List<WorkOverOrder> getMyWorkOverList(Integer page, Integer pageSize, String workNo, Long queryStartDate, Long queryEndDate) {
        return workOverDao.getMyWorkOverList((page-1)*pageSize,pageSize,workNo,queryStartDate,queryEndDate);
    }

    @Override
    public Integer countTotal(String workNo, Long queryStartDate, Long queryEndDate) {
        return workOverDao.countTotal(workNo,queryStartDate,queryEndDate);
    }

    @Override
    public List<WorkOverOrder> getWorkOverApprovalList(Integer page, Integer pageSize, Integer deptId, Long queryStartDate, Long queryEndDate) {
        return workOverDao.getWorkOverApprovalList((page-1)*pageSize,pageSize,deptId,queryStartDate,queryEndDate);
    }

    @Override
    public Integer countApprovalTotal(Integer deptId, Long queryStartDate, Long queryEndDate) {
        return workOverDao.countApprovalTotal(deptId,queryStartDate,queryEndDate);
    }

    @Override
    public IBaseDao getBaseDao() {
        return workOverDao;
    }

    @Override
    public String getTableName() {
        return "work_overtime";
    }

    @Override
    public Class<WorkOverOrder> getClassEntity() {
        return WorkOverOrder.class;
    }
}
