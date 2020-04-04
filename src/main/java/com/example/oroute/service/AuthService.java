package com.example.oroute.service;

import com.example.oroute.jpa.DaoSupport;
import com.example.oroute.model.auth.Auth;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.oroute.utils.MD5Util.encode;

/**
* @Author mini_oj
* @category
* @Description 请求认证服务类
* @Date 2020-04-04 18:54
* @Param
**/
@Transactional
@Service("AuthService")
public class AuthService extends DaoSupport<Auth> {
    private final JdbcTemplate jdbcTemplate;

    public AuthService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
    * @Author mini_oj
    * @category
    * @Description API访问授权信息插入
    * @Date 2020-04-04 19:04
    * @Param
    **/
    public String insertAuth(Auth auth) {
        // 加密
        String authId = auth.getAuthPwd();
        String authIdMd5 = encode(authId);
        String authPwd = auth.getAuthPwd();
        String authPwdMd5 = encode(authPwd);
        auth.setAuthIdMd5(authIdMd5);
        auth.setAuthPwdMd5(authPwdMd5);
        save(auth);

        // 返回账号
        Long uid = auth.getId();
        return String.valueOf(uid);
    }

    /**
    * @Author mini_oj
    * @category 授权验证
    * @Description 验证该请求是否获取授权
    * @Date 2020-04-04 19:10
    * @Param
    **/
    public boolean userAuth(String authUserId, String authPassword) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append("   * ");
        sql.append(" FROM ");
        sql.append("   oroute_api_auth a ");
        sql.append(" WHERE ");
        sql.append("   a.auth_id_md5 = ? ");
        sql.append("   AND a.auth_pwd_md5 = ? ");


        List<Object> params = new ArrayList<>();
        params.add(authUserId);
        params.add(authPassword);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql.toString(), params.toArray());
        if (maps.size() > 0) {
            return true;
        }
        return false;
    }
}
