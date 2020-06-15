package com.linbin.modules.wage.service.impl;

import com.linbin.modules.wage.dao.WageDao;
import com.linbin.modules.wage.entity.Wage;
import com.linbin.modules.wage.service.WageService;
import com.linbin.system.dao.IBaseDao;
import com.linbin.system.impl.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/14 19:32
 * @Description:
 */
@Service
public class WageServiceImpl extends IBaseServiceImpl <Wage> implements WageService {

    @Autowired
    private WageDao wageDao;

    @Override
    public List<Wage> getMyWageList(Integer page, Integer pageSize, String realName, Integer queryYear, Integer queryMonth) {
        return wageDao.getMyWageList((page - 1) * pageSize ,pageSize,realName,queryYear,queryMonth);
    }

    @Override
    public Integer countTotal(String realName, Integer queryYear, Integer queryMonth) {
        return wageDao.countTotal(realName,queryYear,queryMonth);
    }

    @Override
    public IBaseDao getBaseDao() {
        return wageDao;
    }

    @Override
    public String getTableName() {
        return "wage";
    }

    @Override
    public Class<Wage> getClassEntity() {
        return Wage.class;
    }
}
