package com.linbin.modules.wage.dao;

import com.linbin.modules.wage.entity.Wage;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/14 19:31
 * @Description:
 */
@Mapper
public interface WageDao extends IBaseDao {
    List<Wage> getMyWageList(Integer index , Integer pageSize , String realName , Integer queryYear , Integer queryMonth);
    Integer countTotal(String realName , Integer queryYear , Integer queryMonth);
}
