package com.linbin.modules.OA.dao;

import com.linbin.modules.OA.entity.WorkOverOrder;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/8 16:58
 * @Description:
 */
@Mapper
public interface WorkOverDao extends IBaseDao {
    List<WorkOverOrder> getMyWorkOverList(Integer index , Integer pageSize , String workNo , Long queryStartDate , Long queryEndDate);
    Integer countTotal(String workNo , Long queryStartDate , Long queryEndDate);
    List<WorkOverOrder> getWorkOverApprovalList(Integer index , Integer pageSize , Integer deptId , Long queryStartDate , Long queryEndDate);
    Integer countApprovalTotal(Integer deptId , Long queryStartDate , Long queryEndDate);
}
