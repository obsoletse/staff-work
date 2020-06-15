package com.linbin.modules.OA.service;

import com.linbin.modules.OA.entity.InBox;
import com.linbin.system.service.IBaseService;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/11 18:05
 * @Description:
 */
public interface InBoxService extends IBaseService<InBox> {
    Integer getReceiveBoxCount(Integer userId);
    Integer getBinBoxCount(Integer userId);
    List<InBox> getReceiveBoxList(Integer page,Integer pageSize,Integer userId);
    List<InBox> getBinBoxList(Integer page,Integer pageSize,Integer userId);
    void updateReceiveStatus(Integer status , Integer id);
    void updateEmailStatus(Integer id);
}
