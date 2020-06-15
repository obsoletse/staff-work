package com.linbin.modules.department.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/9 16:22
 * @Description:
 */
@Data
public class Department implements Serializable {
    private Integer id;

    private Integer parentId;

    private String deptName;

    private String deptNameEn;

    private String deptAddress;

    private Integer deptType;

    private String deptCode;

    private String description;

    private Integer status;

    @Transient
    private List<Department> children;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", deptName='" + deptName + '\'' +
                ", deptNameEn='" + deptNameEn + '\'' +
                ", deptAddress='" + deptAddress + '\'' +
                ", deptType='" + deptType + '\'' +
                ", deptCode='" + deptCode + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", children='" + children + '\'' +
                '}';
    }
}
