package com.asiainfo.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 登陆日志表实体
 * 
 * @author zhangzhiwang
 * @date 2019年1月22日 下午3:58:07
 */
public class LoginLog implements Serializable {
	private static final long serialVersionUID = -1853000338879724915L;

	int loginLogId;
	int userId;
	String ip;
	Date loginDatetime;

	public int getLoginLogId() {
		return loginLogId;
	}

	public void setLoginLogId(int loginLogId) {
		this.loginLogId = loginLogId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLoginDatetime() {
		return loginDatetime;
	}

	public void setLoginDatetime(Date loginDatetime) {
		this.loginDatetime = loginDatetime;
	}

	@Override
	public String toString() {
		return "LoginLog [loginLogId=" + loginLogId + ", userId=" + userId + ", ip=" + ip + ", loginDatetime="
				+ loginDatetime + "]";
	}

}
