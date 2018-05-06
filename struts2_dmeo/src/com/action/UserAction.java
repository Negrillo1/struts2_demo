package com.action;


import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang.xwork.StringUtils;

import utils.VerifyCode;

import com.dao.UserDao;
import com.dao.UserDaoImpl;
import com.entity.User;

import freemarker.template.utility.StringUtil;

public class UserAction extends SuperAction{

	private String username;
	private String password;
	private String verifycode;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}
	/*
	 * 用户登录功能
	 */
	public String login() throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		user.setPassword(password);
		user.setUsername(username);
		String codeSession = (String) session.getAttribute("code");
		//验证验证码
		if(StringUtils.isEmpty(codeSession)) {
			session.setAttribute("msg", "没有生成验证码信息");
			return "login";
		}
		if(StringUtils.isEmpty(verifycode)) {
			session.setAttribute("msg", "未填写验证码信息");
			return "login";
		}
		if(codeSession.equalsIgnoreCase(verifycode)) {
			//验证用户名和密码
			UserDao userDao = new UserDaoImpl();
			User u = userDao.login(user);
			if(u!=null) {
				session.setAttribute("user", user.getUsername());
				session.setAttribute("msg", ""); 
				return "loginsuccess";
			} else {
				session.setAttribute("msg", "用户名或密码不存在");
				return "login";
			}
		} else {
			session.setAttribute("msg", "验证码错误");
			return "login";
		}
	}
	/*
	 * 产生验证码
	 */
	public void createCode() throws IOException {
		//
		response.setHeader("Expires", "-1");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "-1"); 
		VerifyCode vc = VerifyCode.Instance();
		String code = vc.getString();
		session.setAttribute("code", code);
		ImageIO.write(vc.getImage(),"jpg", response.getOutputStream());
	}
	/*
	 * 登录验证
	 */
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if("".equals(username)) {
			this.addFieldError("usernameError", "用户名不能为空");
		}
		if("".equals(password)) {
			this.addFieldError("passwordError", "密码不能为空");
		}
		super.validate();
	}
}
