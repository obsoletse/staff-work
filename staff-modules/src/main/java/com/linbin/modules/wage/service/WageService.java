package com.linbin.modules.wage.service;

import com.linbin.modules.wage.entity.Wage;
import com.linbin.system.service.IBaseService;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/14 19:31
 * @Description:
 */
public interface WageService extends IBaseService<Wage> {
    List<Wage> getMyWageList(Integer page ,Integer pageSize ,String realName , Integer queryYear , Integer queryMonth);
    Integer countTotal(String realName , Integer queryYear , Integer queryMonth);
}
