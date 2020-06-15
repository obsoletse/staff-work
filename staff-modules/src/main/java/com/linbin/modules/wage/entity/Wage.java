package com.linbin.modules.wage.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Wage {
    private Integer id;

    private String workNo;

    private String name;

    private String deptName;

    private String post;

    private BigDecimal basicWage;

    private BigDecimal finalWage;

    private Integer wageYear;

    private Integer wageMonth;

    private BigDecimal fiveMoney;

    private BigDecimal subsidise;

    private BigDecimal awardMoney;

    private BigDecimal finedMoney;
}