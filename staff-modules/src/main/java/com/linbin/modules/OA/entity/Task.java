package com.linbin.modules.OA.entity;

import lombok.Data;

/**
 * @Author: LinBin
 * @Date: 2020/5/10 13:03
 * @Description:
 */
@Data
public class Task {
    private Integer id;

    private String taskName;

    private Integer proId;

    private String proName;

    private Integer taskCreator;

    private String taskCreatorName;

    private Integer taskWorker;

    private String taskWorkerName;

    private Integer status;

    private Integer priority;

    private String description;
}
