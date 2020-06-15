package com.linbin.modules.evaluation.service;

import com.linbin.modules.evaluation.entity.Evaluation;
import com.linbin.system.service.IBaseService;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/17 9:56
 * @Description:
 */
public interface EvaluationService extends IBaseService<Evaluation> {
    List<Evaluation> getMyEvaluationList(Integer page , Integer pageSize , String workNo , Long queryStartDate , Long queryEndDate ,Integer queryStatus);
    Integer countTotal(String workNo , Long queryStartDate , Long queryEndDate , Integer queryStatus);
    Evaluation queryOneByDate(Long date,String workNo);
    List<Evaluation> getAllEvaluationList(Integer page , Integer pageSize , Integer deptId , Long queryStartDate , Long queryEndDate ,Integer queryStatus);
    Integer countAllTotal(Integer deptId , Long queryStartDate , Long queryEndDate , Integer queryStatus);
    List<Evaluation> getMyEvaluationList1(Integer page , Integer pageSize , String workNo , Long queryStartDate , Long queryEndDate);
    Integer countTotal1(String workNo , Long queryStartDate , Long queryEndDate);
}
