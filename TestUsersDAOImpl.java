package service.impl;

import org.junit.Test;

import com.imooc.entity.Users;

import junit.framework.Assert;
import service.UsersDAO;

public class TestUsersDAOImpl {

	@Test
	public void testUsersLogin() {
		
		Users u=new Users(1,"Tom","123456");
		UsersDAO udao=new UsersDAOImpl();
		Assert.assertEquals(true, udao.usersLogin(u));
	}
}
