package com.asiainfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asiainfo.dao.LoginLogDao;
import com.asiainfo.dao.UserDao;
import com.asiainfo.domain.LoginLog;
import com.asiainfo.domain.User;

/**
 * 用户操作的Service
 * 
 * @author zhangzhiwang
 * @date 2019年1月23日 下午1:40:23
 */
@Service
public class UserService {
	private UserDao userDao;
	private LoginLogDao loginLogDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setLoginLogDao(LoginLogDao loginLogDao) {
		this.loginLogDao = loginLogDao;
	}

	/**
	 * 是否有匹配的用户
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @author zhangzhiwang
	 * @date 2019年1月23日 下午1:43:36
	 */
	public boolean hasMatchUser(String userName, String password) {
		int count = userDao.getCount(userName, password);
		return count > 0;
	}

	/**
	 * 通过用户名查找用户
	 * 
	 * @param userName
	 * @return
	 * @author zhangzhiwang
	 * @date 2019年1月23日 下午1:44:53
	 */
	public User getUserByName(String userName) {
		return userDao.getByName(userName);
	}
	
	@Transactional
	public void loginSuccess(User user) {
		user.setCredits(user.getCredits() + 5);
		userDao.updateUser(user);
		
		LoginLog loginLog = new LoginLog();
		loginLog.setUserId(user.getUserId());
		loginLog.setIp(user.getLastIp());
		loginLog.setLoginDatetime(user.getLastVisit());
		loginLogDao.insert(loginLog);
	}
}
