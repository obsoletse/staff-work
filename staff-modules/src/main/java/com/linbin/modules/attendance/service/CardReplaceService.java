package com.linbin.modules.attendance.service;

import com.linbin.modules.attendance.entity.CardReplace;
import com.linbin.system.service.IBaseService;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/14 14:41
 * @Description:
 */
public interface CardReplaceService extends IBaseService<CardReplace> {
    CardReplace getOneApprovalInfo(String username , Long clockTime);
    List<CardReplace> getCardReplaceList(Integer page , Integer pageSize , Integer deptId , String queryType , String queryStatus , String queryApprovalStatus);
    Integer countTotal(Integer deptId, String queryType , String queryStatus, String queryApprovalStatus);
}
