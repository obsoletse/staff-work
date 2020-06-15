package com.linbin.base.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: LinBin
 * @Date: 2020/4/24 15:54
 * @Description:
 */
@Data
public class UserRole implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer roleId;

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
