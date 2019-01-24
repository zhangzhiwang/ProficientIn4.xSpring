package com.asiainfo.domain;

/**
 * 封装登陆参数
 * 
 * @author zhangzhiwang
 * @date 2019年1月23日 下午8:31:50
 */
public class LoginCommand {
	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginCommand [userName=" + userName + ", password=" + password + "]";
	}

}
