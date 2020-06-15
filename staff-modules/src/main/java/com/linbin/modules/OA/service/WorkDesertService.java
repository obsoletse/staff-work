package com.linbin.modules.OA.service;

import com.linbin.modules.OA.entity.WorkDesertOrder;
import com.linbin.system.service.IBaseService;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/8 17:44
 * @Description:
 */
public interface WorkDesertService extends IBaseService<WorkDesertOrder> {
    List<WorkDesertOrder> getMyWorkDesertList(Integer page , Integer pageSize , String workNo , Long queryStartDate , Long queryEndDate);
    Integer countTotal(String workNo , Long queryStartDate , Long queryEndDate);
    List<WorkDesertOrder> getWorkDesertApprovalList(Integer page , Integer pageSize , Integer deptId , Long queryStartDate , Long queryEndDate);
    Integer countApprovalTotal(Integer deptId , Long queryStartDate , Long queryEndDate);
}
