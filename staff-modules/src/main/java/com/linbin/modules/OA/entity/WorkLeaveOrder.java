package com.linbin.modules.OA.entity;

import lombok.Data;

/**
 * @Author: LinBin
 * @Date: 2020/5/7 21:26
 * @Description:
 */
@Data
public class WorkLeaveOrder {
    private Integer id;

    private String workNo;

    private String applyName;

    private Long workLeaveStartTime;

    private Long workLeaveEndTime;

    private String workLeaveReason;

    private Integer workLeaveDay;

    private Integer status;

    private Long submitTime;

}
