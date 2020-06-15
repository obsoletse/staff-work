package com.linbin.modules.OA.dao;

import com.linbin.modules.OA.entity.WorkOutOrder;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/8 15:01
 * @Description:
 */
@Mapper
public interface WorkOutDao extends IBaseDao {
    List<WorkOutOrder> getMyWorkOutList(Integer index , Integer pageSize , String workNo , Long queryStartDate , Long queryEndDate);
    Integer countTotal(String workNo , Long queryStartDate , Long queryEndDate);
    List<WorkOutOrder> getWorkOutApprovalList(Integer index , Integer pageSize , Integer deptId , Long queryStartDate , Long queryEndDate);
    Integer countApprovalTotal(Integer deptId , Long queryStartDate , Long queryEndDate);
}
