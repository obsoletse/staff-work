package com.linbin.modules.OA.dao;

import com.linbin.modules.OA.entity.WorkDesertOrder;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/8 17:43
 * @Description:
 */
@Mapper
public interface WorkDesertDao extends IBaseDao {
    List<WorkDesertOrder> getMyWorkDesertList(Integer index , Integer pageSize , String workNo , Long queryStartDate , Long queryEndDate);
    Integer countTotal(String workNo , Long queryStartDate , Long queryEndDate);
    List<WorkDesertOrder> getWorkDesertApprovalList(Integer index , Integer pageSize , Integer deptId , Long queryStartDate , Long queryEndDate);
    Integer countApprovalTotal(Integer deptId , Long queryStartDate , Long queryEndDate);
}
