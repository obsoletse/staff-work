package com.linbin.modules.OA.service.impl;

import com.linbin.modules.OA.dao.UseCarDao;
import com.linbin.modules.OA.entity.UseCarOrder;
import com.linbin.modules.OA.service.UseCarService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/8 17:44
 * @Description:
 */
@Service
public class UseCarServiceImpl extends IBaseServiceImpl<UseCarOrder> implements UseCarService {

    @Autowired
    private UseCarDao useCarDao;

    @Override
    public List<UseCarOrder> getMyUseCarList(Integer page, Integer pageSize, String workNo, Long queryStartDate, Long queryEndDate) {
        return useCarDao.getMyUseCarList((page-1)*pageSize,pageSize,workNo,queryStartDate,queryEndDate);
    }

    @Override
    public Integer countTotal(String workNo, Long queryStartDate, Long queryEndDate) {
        return useCarDao.countTotal(workNo,queryStartDate,queryEndDate);
    }

    @Override
    public List<UseCarOrder> getUseCarApprovalList(Integer page, Integer pageSize, Integer deptId, Long queryStartDate, Long queryEndDate) {
        return useCarDao.getUseCarApprovalList((page-1)*pageSize,pageSize,deptId,queryStartDate,queryEndDate);
    }

    @Override
    public Integer countApprovalTotal(Integer deptId, Long queryStartDate, Long queryEndDate) {
        return  useCarDao.countApprovalTotal(deptId,queryStartDate,queryEndDate);
    }
    
    @Override
    public IBaseDao getBaseDao() {
        return useCarDao;
    }

    @Override
    public String getTableName() {
        return "use_car";
    }

    @Override
    public Class<UseCarOrder> getClassEntity() {
        return UseCarOrder.class;
    }
}
