package com.linbin.modules.OA.entity;

import lombok.Data;

/**
 * @Author: LinBin
 * @Date: 2020/5/7 22:19
 * @Description:
 */
@Data
public class WorkBusinessOrder {
    private Integer id;

    private String workNo;

    private String applyName;

    private Long workBusinessStartTime;

    private Long workBusinessEndTime;

    private String workBusinessReason;

    private Integer workBusinessDay;

    private String workBusinessPlace;

    private Integer status;

    private Long submitTime;
}
