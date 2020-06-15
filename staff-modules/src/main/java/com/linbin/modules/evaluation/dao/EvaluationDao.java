package com.linbin.modules.evaluation.dao;

import com.linbin.modules.evaluation.entity.Evaluation;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/17 9:55
 * @Description:
 */
@Mapper
public interface EvaluationDao extends IBaseDao {
    List<Evaluation> getMyEvaluationList(Integer index , Integer pageSize , String workNo , Long queryStartDate , Long queryEndDate , Integer queryStatus);
    Integer countTotal(String workNo , Long queryStartDate , Long queryEndDate , Integer queryStatus);
    List<Evaluation> getMyEvaluationList1(Integer index , Integer pageSize , String workNo , Long queryStartDate , Long queryEndDate);
    Integer countTotal1(String workNo , Long queryStartDate , Long queryEndDate);
    Evaluation queryOneByDate(Long date,String workNo);
    List<Evaluation> getAllEvaluationList(Integer index , Integer pageSize , Integer deptId , Long queryStartDate , Long queryEndDate ,Integer queryStatus);
    Integer countAllTotal(Integer deptId , Long queryStartDate , Long queryEndDate , Integer queryStatus);
}
