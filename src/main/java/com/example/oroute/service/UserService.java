package com.example.oroute.service;

import com.example.oroute.jpa.DaoSupport;
import com.example.oroute.model.user.User;
import com.example.oroute.utils.DBProperties;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.oroute.utils.DesUtils.encode;

@Transactional
@Service
public class UserService extends DaoSupport<User> {
    private final JdbcTemplate jdbcTemplate;
    private Long idStart = Long.valueOf(DBProperties.getString("mysql-id-start"));

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
    * @Author mini_oj
    * @category 注册
    * @Description 用户注册账号信息
    * @Date 2020-03-28 21:52
    * @Param
    * @return uid:系统唯一编码
    **/
    public String insertUser(User user) {
        // 密码校验
        String password = user.getPassword();
        String passwordDes = encode(password);
        user.setPassword(passwordDes);
        save(user);

        // 返回账号
        Long uid = user.getId() + idStart;
        return String.valueOf(uid);
    }

    /**
    * @Author mini_oj
    * @category 账号修改
    * @Description 用户账号信息修改
    * @Date 2020-03-28 22:26
    * @Param
    **/
    public String updateUser(User user) {
        Long userId = user.getId() - idStart;
        String password = user.getPassword();
        Boolean legal = checkLegal(userId, password);
        if (legal) {
            update(user);
            return "个人信息信息修改成功";
        } else {
            return "用户名或密码不正确，请重试";
        }
    }

    /**
    * @Author mini_oj
    * @category 登录
    * @Description 登录校验
    * @Date 2020-03-28 22:57
    * @Param
    **/
    public String login(String params) {
        JSONObject jsonObject = new JSONObject(params);
        Long userId = jsonObject.optLong("userId") - idStart;
        String password = jsonObject.optString("password");
        Boolean legal = checkLegal(userId, password);
        if (legal) {
            return "登录成功";
        }
        return "登录失败，用户名或密码校验出错";
    }

    /**
    * @Author mini_oj
    * @category 密码重置
    * @Description 密码重置
    * @Date 2020-03-28 23:07
    * @Param
    **/
    public String resetPassword(String params) {
        JSONObject jsonObject = new JSONObject(params);
        Long userId = jsonObject.optLong("userId") - idStart;
        String passwordOld = jsonObject.optString("passwordOld");
        Boolean legal = checkLegal(userId, passwordOld);
        if (legal) {
            String passwordNew = jsonObject.optString("passwordNew");
            Integer updateRow = updatePwd(userId, passwordNew);
            if (updateRow > 0) {
                return "密码重置成功";
            }
            return "密码重置失败，请重试";
        }
        return "用户名或密码校验未通过，请检查旧用户名密码";
    }

    /**
    * @Author mini_oj
    * @category
    * @Description 获取个人信息
    * @Date 2020-03-28 23:23
    * @Param
    **/
    public String getUser(String params) {
        JSONObject jsonObject = new JSONObject(params);
        Long userId = jsonObject.optLong("userId") - idStart;
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append("   * ");
        sql.append(" FROM ");
        sql.append("   oroute_user u ");
        sql.append(" WHERE ");
        sql.append("   u.id = ? ");

        Map<String, Object> map = jdbcTemplate.queryForMap(sql.toString(), userId);
        if (map.containsKey("id")) {
            JSONObject json = new JSONObject(map);
            return json.toString();
        }
        return null;
    }

    /**
    * @Author mini_oj
    * @category 登录校验
    * @Description 查询数据库，检查用户名密码是否能够对应
    * @Date 2020-03-28 22:42
    * @Param
    **/
    private Boolean checkLegal(Long userId, String password) {
        String passwordDes = encode(password);
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append("   * ");
        sql.append(" FROM ");
        sql.append("   oroute_user u ");
        sql.append(" WHERE ");
        sql.append("   u.id = ? ");
        sql.append("   AND u.password = ? ");

        List<Object> params = new ArrayList<>();
        params.add(userId);
        params.add(passwordDes);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql.toString(), params.toArray());
        if (maps.size() > 0) {
            return true;
        }
        return false;
    }

    /**
    * @Author mini_oj
    * @category
    * @Description
    * @Date 2020-03-28 23:13
    * @Param
    * @return int 更新行数
    **/
    private Integer updatePwd(Long userId, String password) {
        String passwordDes = encode(password);
        StringBuffer sql = new StringBuffer();
        sql.append(" UPDATE ");
        sql.append("   oroute_user u ");
        sql.append(" SET ");
        sql.append("   u.password = ? ");
        sql.append(" WHERE ");
        sql.append("   u.id = ? ");

        List<Object> params = new ArrayList<>();
        params.add(passwordDes);
        params.add(userId);
        return jdbcTemplate.update(sql.toString(), params.toArray());
    }
}
