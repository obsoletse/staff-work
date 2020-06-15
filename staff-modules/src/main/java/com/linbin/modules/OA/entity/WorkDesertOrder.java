package com.linbin.modules.OA.entity;

import lombok.Data;

/**
 * @Author: LinBin
 * @Date: 2020/5/8 17:37
 * @Description:
 */
@Data
public class WorkDesertOrder {
    private Integer id;

    private String workNo;

    private String applyName;

    private Long leaveTime;

    private String workDesertReason;

    private Integer status;

    private Long submitTime;

}
