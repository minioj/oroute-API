package com.example.oroute.model.auth;

import com.example.oroute.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "oroute_api_auth")
public class Auth extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "authId")
    @NotBlank(message="不能为空")
    @ApiModelProperty(value="授权用户名",name="authId",required=true)
    private String authId;

    @Column(name = "authPwd")
    @NotBlank(message="不能为空")
    @ApiModelProperty(value="授权用户密码",name="authPwd",required=true)
    private String authPwd;

    @Column(name = "authIdMd5")
    @ApiModelProperty(value="授权用户名MD5",name="authIdMd5")
    private String authIdMd5;

    @Column(name = "authPwdMd5")
    @ApiModelProperty(value="授权用户密码MD5",name="authPwdMd5", notes = "0:男 1:女 2:保密")
    private String authPwdMd5;

    public Auth(@NotBlank(message = "不能为空") String authId, @NotBlank(message = "不能为空") String authPwd, String authIdMd5, String authPwdMd5) {
        this.authId = authId;
        this.authPwd = authPwd;
        this.authIdMd5 = authIdMd5;
        this.authPwdMd5 = authPwdMd5;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAuthPwd() {
        return authPwd;
    }

    public void setAuthPwd(String authPwd) {
        this.authPwd = authPwd;
    }

    public String getAuthIdMd5() {
        return authIdMd5;
    }

    public void setAuthIdMd5(String authIdMd5) {
        this.authIdMd5 = authIdMd5;
    }

    public String getAuthPwdMd5() {
        return authPwdMd5;
    }

    public void setAuthPwdMd5(String authPwdMd5) {
        this.authPwdMd5 = authPwdMd5;
    }
}