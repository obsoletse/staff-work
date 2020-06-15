package com.linbin.modules.evaluation.entity;

import lombok.Data;

@Data
public class Evaluation {
    private Integer id;

    private String workNo;

    private String name;

    private Long date;

    private Float selfMark;

    private String selfDescription;

    private Integer status;

    private String approvalName;

    private Float approvalMark;

    private String approvalDescription;

}