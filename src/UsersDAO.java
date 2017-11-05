package service;

import com.imooc.entity.Users;

//用户业务逻辑借口
public interface UsersDAO {

	//用户登录方法
	public boolean usersLogin(Users u);
}
