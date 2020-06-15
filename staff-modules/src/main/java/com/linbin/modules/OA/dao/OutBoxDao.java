package com.linbin.modules.OA.dao;

import com.linbin.modules.OA.entity.OutBox;
import com.linbin.system.dao.IBaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/11 18:05
 * @Description:
 */
@Mapper
public interface OutBoxDao extends IBaseDao {
    Integer getSendBoxCount(Integer userId);
    Integer getDraftBoxCount(Integer userId);
    List<OutBox> getSendBoxList(Integer index , Integer pageSize , Integer userId);
    List<OutBox> getDraftBoxList(Integer index , Integer pageSize , Integer userId);
}
