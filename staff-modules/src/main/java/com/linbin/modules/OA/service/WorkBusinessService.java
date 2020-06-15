package com.linbin.modules.OA.service;

import com.linbin.modules.OA.entity.WorkBusinessOrder;
import com.linbin.system.service.IBaseService;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/7 22:19
 * @Description:
 */
public interface WorkBusinessService extends IBaseService<WorkBusinessOrder> {
    List<WorkBusinessOrder> getMyWorkBusinessList(Integer page , Integer pageSize , String workNo , Long queryStartDate , Long queryEndDate);
    Integer countTotal(String workNo , Long queryStartDate , Long queryEndDate);
    List<WorkBusinessOrder> getWorkBusinessApprovalList(Integer page , Integer pageSize , Integer deptId , Long queryStartDate , Long queryEndDate);
    Integer countApprovalTotal(Integer deptId , Long queryStartDate , Long queryEndDate);
}
