package com.linbin.modules.OA.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: LinBin
 * @Date: 2020/5/8 17:37
 * @Description:
 */
@Data
public class UseCarOrder {
    private Integer id;

    private String workNo;

    private String applyName;

    private String carNumber;

    private Long useCarStartTime;

    private Long useCarEndTime;

    private String useCarReason;

    private Integer useCarTime;

    private String destination;

    private BigDecimal km;

    private BigDecimal cost;

    private Integer status;

    private Long submitTime;

}
