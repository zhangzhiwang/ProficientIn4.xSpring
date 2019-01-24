package com.asiainfo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.asiainfo.domain.LoginLog;

/**
 * 登陆日志Dao
 * 
 * @author zhangzhiwang
 * @date 2019年1月23日 下午1:13:36
 */
@Repository
public class LoginLogDao {
	private JdbcTemplate jdbcTemplate;
	private static final String INSERT_LOGIN_LOG_SQL = "insert into t_login_log(user_id,ip,login_datetime) values (?,?,?)";

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void insert(LoginLog loginLog) {
		jdbcTemplate.update(INSERT_LOGIN_LOG_SQL, new Object[]{loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDatetime()});
	}
	
}
