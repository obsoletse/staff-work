package com.linbin.modules.attendance.entity;

import lombok.Data;

@Data
public class CardReplace {
    private Integer id;

    private String username;

    private String applyName;

    private Integer deptId;

    private Integer cardType;

    private Integer errStatus;

    private Long clockTime;

    private String reason;

    private Integer approvalStatus;

    private Long submitTime;

}