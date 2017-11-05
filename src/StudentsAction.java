package com.imooc.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.imooc.entity.Students;
import com.opensymphony.xwork2.ModelDriven;

import service.StudentsDAO;
import service.impl.StudentDAOImpl;

//学生Action类
public class StudentsAction extends SuperAction implements ModelDriven<Students>{

	private static final long serialVersionUID=1L;
	private Students s=new Students();
	//查询所有学生的动作
	public String query() {
		StudentsDAO sdao=new StudentDAOImpl();
		List<Students> list=sdao.queryAllStudents();
		//放进session中
		if(list !=null&&list.size()>0) {
			session.setAttribute("students_list", list);
		}
		return "query_success";
	}
	
	//删除学生动作
	public String delete() {
		StudentsDAO sdao=new StudentDAOImpl();
		String sid=request.getParameter("sid");
		sdao.deleteStudents(sid);//调用删除方法
		return "delete_success";
	}
	
	//添加学生资料
	/*
	public String add() throws ParseException{
		
		Students s=new Students();
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
		Date date=(Date)sdf.parse(request.getParameter("birthday"));
		s.setBirthday(date);
		
		//s.setBirthday(sdf.parse(request.getParameter("birthday")));
		//String birthdayParam=request.getParameter("birthday");
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//Date birthday=sdf.parse(birthdayParam.toString());
		//s.setBirthday(birthday);
		s.setAddress(request.getParameter("address"));
		StudentsDAO sdao=new StudentDAOImpl();
		sdao.addStudents(s);
		return "add_success";
	}*/
	//修改学生资料的动作
	public String modify() {
		
		//获得传递进来的学生编号
		String  sid=request.getParameter("sid");
		StudentsDAO sdao=new StudentDAOImpl();
		Students s=sdao.queryStudentsBySid(sid);
		//保存到会话中
		session.setAttribute("modify_students", s);
		return "modify_success";
	}
	
	//保存修改后的学生资料动作
	public String update() throws Exception{
		
		Students s=new Students();
		s.setSid(request.getParameter("sid"));
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));
		String birthdayParam=request.getParameter("birthday");
		Date birthday=new SimpleDateFormat("yyyy-MM-dd").parse(birthdayParam);
		//s.setBirthday(sdf.parse(request.getParameter("birthday")));
		s.setBirthday(birthday);
		s.setAddress(request.getParameter("address"));
		StudentsDAO sdao=new StudentDAOImpl();
		sdao.updateStudents(s);
		return "update_success";
	}

	//使用模型驱动
	public String add() {
	StudentsDAO sdao=new StudentDAOImpl();
	sdao.addStudents(s);
	return "add_success";
	}
	@Override
	public Students getModel() {
		// TODO Auto-generated method stub
		return s;
	}

	
}
