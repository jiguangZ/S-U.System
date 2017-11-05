package service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.imooc.entity.Students;

import service.StudentsDAO;

public class TestStudentsDAO {

	@Test
	public void testAddAll() {

		StudentsDAO sdao=new StudentDAOImpl();
		List<Students> list=sdao.queryAllStudents();
		
		for(Students stu:list) {
			System.out.println(stu);
		}
	}
	/*
	
	public void testGetNewSid() {
		
		StudentDAOImpl sdao=new StudentDAOImpl();
		System.out.println(sdao.getNewSid());
	}
	*/
	
	@Test
	
	public void testAddStudents() {
		
		Students s=new Students();
		s.setSname("郑继光");
		s.setGender("男");
		s.setBirthday(new Date());
		s.setAddress("安徽");
		
		StudentsDAO sdao=new StudentDAOImpl();
		//Assert.assertArrayEquals(true, sdao.addStudents(s));
	     Assert.assertEquals(true, sdao.addStudents(s));
	}
}
