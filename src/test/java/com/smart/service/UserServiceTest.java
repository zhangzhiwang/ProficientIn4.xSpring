package com.smart.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.asiainfo.domain.User;
import com.asiainfo.service.UserService;

/**
 * 用户服务单元测试
 * 
 * @author zhangzhiwang
 * @date 2019年1月23日 下午5:54:40
 */
@ContextConfiguration("classpath*:/spring-context.xml")
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Test
	public void testHasMatchUser() {
		boolean hasMatchUser = userService.hasMatchUser("admin", "1234567");
		Assert.assertTrue(hasMatchUser);
	}
	
	@Test
	public void testGetUserByName() {
		User user = userService.getUserByName("admin1");
		System.out.println(user);
	}
	
	@Test
	public void testLoginSuccess() {
		User user = new User();
		user.setUserId(1);
		user.setUserName("admin");
		user.setPassword("123456");
		user.setCredits(0);
		user.setLastIp("127.0.0.1");
		user.setLastVisit(new Date());
		
		userService.loginSuccess(user);
	}
}
