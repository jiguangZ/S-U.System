package com.imooc.action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.imooc.entity.Users;
import com.opensymphony.xwork2.ModelDriven;

import service.UsersDAO;
import service.impl.UsersDAOImpl;

public class UsersAction extends SuperAction implements ModelDriven<Users>{

	private static final long serialVersionUID=1L;
	private Users users=new Users();
 
	
	//用户登录动作
	public String login() {
		
		UsersDAO udao=new UsersDAOImpl();
		if(udao.usersLogin(users)) {
			session.setAttribute("loginUsersName", users.getUsername());
			return "login_success";
		}else {
			return "login_failure";
		}
	}
	
	//@SkipValidation
	//用户注销
	@SkipValidation
	public String logout() {
		
		if(session.getAttribute("loginUsersName") != null) {
			session.removeAttribute("loginUsersName");
		}
		
		return "logout_success";
	}
	
	
	@Override
	public void validate() {
		
		if("".equals(users.getUsername().trim())){
			this.addFieldError("usernameError","用户名不能为空！");
		}if(users.getPassword().length()<6) {
			this.addFieldError("passwordError","密码不能少于6位！");
		}
	}

	@Override
	public Users getModel() {
		
		return this.users;
	}
}
