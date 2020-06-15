package com.linbin.base.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: LinBin
 * @Date: 2020/4/24 23:39
 * @Description:
 */
@Data
public class RoleMenu implements Serializable {
    private Integer id;
    private Integer roleId;
    private Integer menuId;

    @Override
    public String toString() {
        return "RoleMenu{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", menuId=" + menuId +
                '}';
    }
}
