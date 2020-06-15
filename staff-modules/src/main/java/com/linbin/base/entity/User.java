package com.linbin.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Data
public class User implements Serializable {

    private Integer id;

    private String username;

    private String realName;

    @JsonIgnore
    private String password;

    private String avatar;

    private Long birthday;

    private Integer sex;

    private String email;

    private String phone;

    private String address;

    private Integer deptId;

    private String workNo;

    private String post;

    private Integer isEnabled;

    @Transient
    private String depart;

    @Transient
    private List<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + "data:image/gif;base64..." + '\'' +
                ", birthday=" + birthday +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", deptId=" + deptId +
                ", workNo='" + workNo + '\'' +
                ", post='" + post + '\'' +
                ", isEnabled='" + isEnabled + '\'' +
                ", depart='" + depart + '\'' +
                ", roles=" + roles +
                '}';
    }
}
