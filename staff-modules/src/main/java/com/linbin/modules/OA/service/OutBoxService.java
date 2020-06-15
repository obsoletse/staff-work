package com.linbin.modules.OA.service;

import com.linbin.modules.OA.entity.OutBox;
import com.linbin.system.service.IBaseService;

import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/11 18:05
 * @Description:
 */
public interface OutBoxService extends IBaseService<OutBox> {
    Integer getSendBoxCount(Integer userId);
    Integer getDraftBoxCount(Integer userId);
    List<OutBox> getSendBoxList(Integer page , Integer pageSize , Integer userId);
    List<OutBox> getDraftBoxList(Integer page , Integer pageSize , Integer userId);

}
