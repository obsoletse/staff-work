package com.linbin.modules.OA.service;

import com.linbin.modules.OA.entity.UseCarOrder;
import com.linbin.system.service.IBaseService;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/8 17:44
 * @Description:
 */
public interface UseCarService extends IBaseService<UseCarOrder> {
    List<UseCarOrder> getMyUseCarList(Integer page , Integer pageSize , String workNo , Long queryStartDate , Long queryEndDate);
    Integer countTotal(String workNo , Long queryStartDate , Long queryEndDate);
    List<UseCarOrder> getUseCarApprovalList(Integer page , Integer pageSize , Integer deptId , Long queryStartDate , Long queryEndDate);
    Integer countApprovalTotal(Integer deptId , Long queryStartDate , Long queryEndDate);
}
