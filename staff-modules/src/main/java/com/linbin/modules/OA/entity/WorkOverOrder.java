package com.linbin.modules.OA.entity;

import lombok.Data;

/**
 * @Author: LinBin
 * @Date: 2020/5/8 16:57
 * @Description:
 */
@Data
public class WorkOverOrder {
    private Integer id;

    private String workNo;

    private String applyName;

    private Integer proId;

    private String proName;

    private String workOverReason;

    private Long workOverStartTime;

    private Long workOverEndTime;

    private Integer workOverTime;

    private Integer status;

    private Long submitTime;

}
