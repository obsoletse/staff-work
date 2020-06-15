package com.linbin.modules.studyPlat.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: LinBin
 * @Date: 2020/5/1 11:26
 * @Description:
 */
@Data
public class Course implements Serializable {
    private Integer id;

    private String courseName;

    private Integer modality;

    private String source;

    private String teacher;

    private Integer heat;

    private String description;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", modality=" + modality +
                ", source='" + source + '\'' +
                ", teacher='" + teacher + '\'' +
                ", heat=" + heat +
                ", description=" + description +
                '}';
    }
}
