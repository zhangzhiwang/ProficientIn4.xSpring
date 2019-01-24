package com.asiainfo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.asiainfo.domain.User;

/**
 * 用户Dao
 * 
 * @author zhangzhiwang
 * @date 2019年1月23日 下午12:40:22
 */
@Repository
public class UserDao {
	private JdbcTemplate jdbcTemplate;
	private static final String COUNT_SQL = "select count(*) from t_user where user_name = ? and password = ?";
	private static final String QUERY_BY_NAME_SQL = "select * from t_user where user_name = ?";
	private static final String UPDATE_USER_SQL = "update t_user set user_name = ?, password = ?, credits = ?, last_visit = ?, last_ip = ? where user_id = ?";

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 获取符合制定用户名和密码的用户数量
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @author zhangzhiwang
	 * @date 2019年1月23日 下午12:53:07
	 */
	public int getCount(String userName, String password) {
		// 为了节省时间，一般的参数校验就不写了，详见本工程根目录的《README.md》的说明部分
		return jdbcTemplate.queryForObject(COUNT_SQL, new Object[] { userName, password }, int.class);
	}

	/**
	 * 通过名称精确查询用户
	 * 
	 * @param userName
	 * @return
	 * @author zhangzhiwang
	 * @date 2019年1月23日 下午1:03:09
	 */
	public User getByName(String userName) {
		final User user = new User();
		jdbcTemplate.query(QUERY_BY_NAME_SQL, new Object[] { userName }, new RowCallbackHandler() {

			public void processRow(ResultSet rs) throws SQLException {
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setCredits(rs.getInt("credits"));
				user.setLastVisit(rs.getDate("last_visit"));
				user.setLastIp(rs.getString("last_ip"));
			}
		});

		return user;
	}

	/**
	 * 更新用户信息
	 * 
	 * @author zhangzhiwang
	 * @date 2019年1月23日 下午1:03:58
	 */
	public void updateUser(User user) {
		jdbcTemplate.update(UPDATE_USER_SQL, new Object[] { user.getUserName(), user.getPassword(), user.getCredits(),
				user.getLastVisit(), user.getLastIp(), user.getUserId() });
	}

}
