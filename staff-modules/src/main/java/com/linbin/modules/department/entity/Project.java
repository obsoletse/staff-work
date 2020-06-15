package com.linbin.modules.department.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: LinBin
 * @Date: 2020/4/10 18:11
 * @Description:
 */
@Data
public class Project {
    private Integer id;

    private String proName;

    private BigDecimal proPercentage;

    private Float proUrgency;

    private String description;

    private Integer deptId;

    @Transient
    private String depart;

    private Integer status;

    private Long planStartTime;

    private Long factStartTime;

    private Long planEndTime;

    private Long factEndTime;

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", proName='" + proName + '\'' +
                ", proPercentage=" + proPercentage +
                ", proUrgency=" + proUrgency +
                ", description='" + description + '\'' +
                ", deptId=" + deptId +
                ", depart=" + depart +
                ", status=" + status +
                ", planStartTime=" + planStartTime +
                ", factStartTime=" + factStartTime +
                ", planEndTime=" + planEndTime +
                ", factEndTime=" + factEndTime +
                '}';
    }
}
