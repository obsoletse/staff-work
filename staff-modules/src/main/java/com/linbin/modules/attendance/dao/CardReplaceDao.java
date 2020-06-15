package com.linbin.modules.attendance.dao;

import com.linbin.modules.attendance.entity.CardReplace;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/14 14:40
 * @Description:
 */
@Mapper
public interface CardReplaceDao extends IBaseDao {
    CardReplace getOneApprovalInfo(String username , Long clockTime);
    List<CardReplace> getCardReplaceList(Integer index , Integer pageSize , Integer deptId , String queryType , String queryStatus, String queryApprovalStatus);
    Integer countTotal(Integer deptId, String queryType , String queryStatus, String queryApprovalStatus);
}
