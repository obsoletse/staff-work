package com.linbin.base.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: LinBin
 * @Date: 2020/4/19 14:27
 * @Description:
 */
@Data
public class Role implements Serializable {
    private Integer id;
    private String code;
    private String name;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
