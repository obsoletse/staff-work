package com.linbin.modules.OA.dao;

import com.linbin.modules.OA.entity.WorkBusinessOrder;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/7 22:27
 * @Description:
 */
@Mapper
public interface WorkBusinessDao extends IBaseDao {
    List<WorkBusinessOrder> getMyWorkBusinessList(Integer index , Integer pageSize , String workNo , Long queryStartDate , Long queryEndDate);
    Integer countTotal(String workNo , Long queryStartDate , Long queryEndDate);
    List<WorkBusinessOrder> getWorkBusinessApprovalList(Integer index , Integer pageSize , Integer deptId , Long queryStartDate , Long queryEndDate);
    Integer countApprovalTotal(Integer deptId , Long queryStartDate , Long queryEndDate);
}
