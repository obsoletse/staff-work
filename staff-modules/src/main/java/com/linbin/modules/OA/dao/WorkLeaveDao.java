package com.linbin.modules.OA.dao;

import com.linbin.modules.OA.entity.WorkLeaveOrder;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/7 21:31
 * @Description:
 */
@Mapper
public interface WorkLeaveDao extends IBaseDao {
    List<WorkLeaveOrder> getMyWorkLeaveList(Integer index , Integer pageSize , String workNo , Long queryStartDate , Long queryEndDate);
    Integer countTotal(String workNo , Long queryStartDate , Long queryEndDate);
    List<WorkLeaveOrder> getWorkLeaveApprovalList(Integer index , Integer pageSize , Integer deptId , Long queryStartDate , Long queryEndDate);
    Integer countApprovalTotal(Integer deptId , Long queryStartDate , Long queryEndDate);
}
