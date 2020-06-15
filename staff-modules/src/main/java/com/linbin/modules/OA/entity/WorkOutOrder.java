package com.linbin.modules.OA.entity;

import lombok.Data;

/**
 * @Author: LinBin
 * @Date: 2020/5/8 14:59
 * @Description:
 */
@Data
public class WorkOutOrder {
    private Integer id;

    private String workNo;

    private String applyName;

    private String workOutReason;

    private Long workOutStartTime;

    private Long workOutEndTime;

    private Integer workOutTime;

    private String workOutPlace;

    private Integer status;

    private Long submitTime;

}
