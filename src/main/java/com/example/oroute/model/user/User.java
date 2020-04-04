package com.example.oroute.model.user;

import com.example.oroute.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "oroute_user")
public class User extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "username")
    @NotBlank(message="不能为空")
    @ApiModelProperty(value="用户名",name="username",required=true)
    private String username;

    @Column(name = "password")
    @NotBlank(message="不能为空")
    @ApiModelProperty(value="密码",name="password",required=true)
    private String password;

    @Column(name = "phone")
    @NotBlank(message="不能为空")
    @ApiModelProperty(value="电话",name="phone",required=true)
    private String phone;

    @Column(name = "sex")
    @ApiModelProperty(value="性别",name="sex",required=true, notes = "0:男 1:女 2:保密")
    private String sex;

    @Column(name = "isActive")
    @ApiModelProperty(hidden=true,value="是否有效",name="isActive",notes = "0:无效 1:有效")
    private Integer isActive;

    public User() {
        super();
    }

    public User(String username, String password, String phone, String sex, Integer isActive) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.isActive = isActive;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}