package com.linbin.modules.evaluation.service.impl;

import com.linbin.modules.evaluation.dao.EvaluationDao;
import com.linbin.modules.evaluation.entity.Evaluation;
import com.linbin.modules.evaluation.service.EvaluationService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/17 9:57
 * @Description:
 */
@Service
public class EvaluationServiceImpl extends IBaseServiceImpl<Evaluation> implements EvaluationService {

    @Autowired
    private EvaluationDao evaluationDao;

    @Override
    public List<Evaluation> getMyEvaluationList(Integer page, Integer pageSize, String workNo, Long queryStartDate, Long queryEndDate, Integer queryStatus) {
        return evaluationDao.getMyEvaluationList((page-1)*pageSize,pageSize,workNo,queryStartDate,queryEndDate,queryStatus);
    }

    @Override
    public Integer countTotal(String workNo, Long queryStartDate, Long queryEndDate, Integer queryStatus) {
        return evaluationDao.countTotal(workNo,queryStartDate,queryEndDate,queryStatus);
    }

    @Override
    public List<Evaluation> getMyEvaluationList1(Integer page, Integer pageSize, String workNo, Long queryStartDate, Long queryEndDate) {
        return evaluationDao.getMyEvaluationList1((page-1)*pageSize,pageSize,workNo,queryStartDate,queryEndDate);
    }

    @Override
    public Integer countTotal1(String workNo, Long queryStartDate, Long queryEndDate) {
        return evaluationDao.countTotal1(workNo,queryStartDate,queryEndDate);
    }

    @Override
    public Evaluation queryOneByDate(Long date,String workNo) {
        return evaluationDao.queryOneByDate(date,workNo);
    }

    @Override
    public List<Evaluation> getAllEvaluationList(Integer page, Integer pageSize, Integer deptId, Long queryStartDate, Long queryEndDate, Integer queryStatus) {
        return evaluationDao.getAllEvaluationList((page-1)*pageSize,pageSize,deptId,queryStartDate,queryEndDate,queryStatus);
    }

    @Override
    public Integer countAllTotal(Integer deptId, Long queryStartDate, Long queryEndDate, Integer queryStatus) {
        return evaluationDao.countAllTotal(deptId,queryStartDate,queryEndDate,queryStatus);
    }

    @Override
    public IBaseDao getBaseDao() {
        return evaluationDao;
    }

    @Override
    public String getTableName() {
        return "evaluation";
    }

    @Override
    public Class<Evaluation> getClassEntity() {
        return Evaluation.class;
    }
}
