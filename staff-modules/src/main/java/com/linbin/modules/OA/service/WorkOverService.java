package com.linbin.modules.OA.service;

import com.linbin.modules.OA.entity.WorkOverOrder;
import com.linbin.system.service.IBaseService;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/8 16:58
 * @Description:
 */
public interface WorkOverService extends IBaseService<WorkOverOrder> {
    List<WorkOverOrder> getMyWorkOverList(Integer page , Integer pageSize , String workNo , Long queryStartDate , Long queryEndDate);
    Integer countTotal(String workNo , Long queryStartDate , Long queryEndDate);
    List<WorkOverOrder> getWorkOverApprovalList(Integer page , Integer pageSize , Integer deptId , Long queryStartDate , Long queryEndDate);
    Integer countApprovalTotal(Integer deptId , Long queryStartDate , Long queryEndDate);
}
