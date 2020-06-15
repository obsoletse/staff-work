package com.linbin.modules.OA.service;

import com.linbin.modules.OA.entity.WorkOutOrder;
import com.linbin.system.service.IBaseService;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/8 15:02
 * @Description:
 */
public interface WorkOutService extends IBaseService<WorkOutOrder> {
    List<WorkOutOrder> getMyWorkOutList(Integer page , Integer pageSize , String workNo , Long queryStartDate , Long queryEndDate);
    Integer countTotal(String workNo , Long queryStartDate , Long queryEndDate);
    List<WorkOutOrder> getWorkOutApprovalList(Integer page , Integer pageSize , Integer deptId , Long queryStartDate , Long queryEndDate);
    Integer countApprovalTotal(Integer deptId , Long queryStartDate , Long queryEndDate);
}
