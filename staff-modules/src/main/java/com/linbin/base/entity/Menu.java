package com.linbin.base.entity;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/21 13:42
 * @Description:
 */
@Data
public class Menu implements Serializable {
    private Integer id;

    private Integer parentId;

    private String path;

    private String name;

    private String icon;

    private Integer enabled;

    @Transient
    private List<Menu> children;

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", enabled='" + enabled + '\'' +
                ", children=" + children +
                '}';
    }
}
