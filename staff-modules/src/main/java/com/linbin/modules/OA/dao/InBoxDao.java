package com.linbin.modules.OA.dao;

import com.linbin.modules.OA.entity.InBox;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/11 18:04
 * @Description:
 */
@Mapper
public interface InBoxDao extends IBaseDao {
    Integer getReceiveBoxCount(Integer userId);
    Integer getBinBoxCount(Integer userId);
    List<InBox> getReceiveBoxList(Integer index, Integer pageSize, Integer userId);
    List<InBox> getBinBoxList(Integer index, Integer pageSize, Integer userId);
    void updateReceiveStatus(Integer status ,Integer id);
    void updateEmailStatus(Integer id);
}
