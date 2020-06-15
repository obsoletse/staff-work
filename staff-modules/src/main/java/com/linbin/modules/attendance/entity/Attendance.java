package com.linbin.modules.attendance.entity;

import lombok.Data;

@Data
public class Attendance {
    private Integer id;

    private String username;

    private String realName;

    private Long clockTime;

    private Integer type;

    private Integer status;

    private Integer isApproval;

    private Integer isReplace;

}