package com.linbin.modules.OA.service.impl;

import com.linbin.modules.OA.dao.WorkBusinessDao;
import com.linbin.modules.OA.entity.WorkBusinessOrder;
import com.linbin.modules.OA.service.WorkBusinessService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/7 22:27
 * @Description:
 */
@Service
public class WorkBusinessServiceImpl extends IBaseServiceImpl<WorkBusinessOrder> implements WorkBusinessService {

    @Autowired
    private WorkBusinessDao workBusinessDao;

    @Override
    public List<WorkBusinessOrder> getMyWorkBusinessList(Integer page, Integer pageSize, String workNo, Long queryStartDate, Long queryEndDate) {
        return workBusinessDao.getMyWorkBusinessList((page - 1) * pageSize ,pageSize,workNo,queryStartDate,queryEndDate);
    }

    @Override
    public Integer countTotal(String workNo, Long queryStartDate, Long queryEndDate) {
        return workBusinessDao.countTotal(workNo,queryStartDate,queryEndDate);
    }

    @Override
    public List<WorkBusinessOrder> getWorkBusinessApprovalList(Integer page, Integer pageSize, Integer deptId, Long queryStartDate, Long queryEndDate) {
        return workBusinessDao.getWorkBusinessApprovalList((page-1)*pageSize,pageSize,deptId,queryStartDate,queryEndDate);
    }

    @Override
    public Integer countApprovalTotal(Integer deptId, Long queryStartDate, Long queryEndDate) {
        return  workBusinessDao.countApprovalTotal(deptId,queryStartDate,queryEndDate);
    }
    
    @Override
    public IBaseDao getBaseDao() {
        return workBusinessDao;
    }

    @Override
    public String getTableName() {
        return "work_business";
    }

    @Override
    public Class<WorkBusinessOrder> getClassEntity() {
        return WorkBusinessOrder.class;
    }
}
