package com.asiainfo.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.asiainfo.domain.LoginCommand;
import com.asiainfo.domain.User;
import com.asiainfo.service.UserService;

/**
 * 登录控制器
 * 
 * @author zhangzhiwang
 * @date 2019年1月23日 下午8:24:10
 */
@Controller
public class LoginController {
	private  UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/index.html")
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping(value="/loginCheck.html")
	public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand) {
		boolean hasMatchUser = userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
		
		if(!hasMatchUser) {
			return new ModelAndView("login", "error", "用户名或密码错误");
		}
		
		User user = userService.getUserByName(loginCommand.getUserName());
		user.setLastIp(request.getLocalAddr());
		user.setLastVisit(new Date());
		userService.loginSuccess(user);
		
		request.getSession().setAttribute("user", user);
		return new ModelAndView("main");
	}
}
