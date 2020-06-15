package com.linbin.modules.OA.service;

import com.linbin.modules.OA.entity.WorkLeaveOrder;
import com.linbin.system.service.IBaseService;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/7 21:30
 * @Description:
 */
public interface WorkLeaveService extends IBaseService<WorkLeaveOrder> {
    List<WorkLeaveOrder> getMyWorkLeaveList(Integer page , Integer pageSize , String workNo , Long queryStartDate , Long queryEndDate);
    Integer countTotal(String workNo , Long queryStartDate , Long queryEndDate);
    List<WorkLeaveOrder> getWorkLeaveApprovalList(Integer page , Integer pageSize , Integer deptId , Long queryStartDate , Long queryEndDate);
    Integer countApprovalTotal(Integer deptId , Long queryStartDate , Long queryEndDate);
}
