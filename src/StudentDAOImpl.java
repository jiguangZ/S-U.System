package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.imooc.entity.Students;

import db.MyHibernateSessionFactory;
import service.StudentsDAO;

public class StudentDAOImpl implements StudentsDAO {

	@Override
	public List<Students> queryAllStudents() {
		Transaction tx=null;
		List<Students> list=null;
		try {
			Session session=MyHibernateSessionFactory.getSeesionFactory().getCurrentSession();
		    tx=session.beginTransaction();
			Query query=session.createQuery("from Students");
		    list=query.list();
		    tx.commit();
		    return list;
		}catch(Exception e) {
			
			e.printStackTrace();
			tx.rollback();
			tx.commit();
			return list;
			
		}finally {
			if(tx !=null) {
				tx=null;
			}
		}
	}

	@Override
	public Students queryStudentsBySid(String sid) {
		
		Transaction tx=null;
		Students s=null;
		try {
			
		Session session=MyHibernateSessionFactory.getSeesionFactory().getCurrentSession();
		tx=session.beginTransaction();
		s=(Students) session.get(Students.class, sid);
		tx.commit();
		return s;
		}catch(Exception e) {
			e.printStackTrace();
			tx.commit();
			return s;
		}finally {
			
			if(tx !=null) {
				tx=null;
			}
		}
	}
     
	@Override
	public boolean addStudents(Students s) {
		//设置学生的学号
		s.setSid(getNewSid());
		Transaction tx=null;
		try {
			
		Session session=MyHibernateSessionFactory.getSeesionFactory().getCurrentSession();
		tx=session.beginTransaction();
		
		session.save(s);
		tx.commit();
		return true;
		}catch(Exception e) {
			e.printStackTrace();
			tx.commit();
			return false;
		}finally {
			
			if(tx !=null) {
				tx=null;
			}
		}
	}

	@Override
	public boolean updateStudents(Students s) {
		Transaction tx=null;
		try {
			
	     Session session=MyHibernateSessionFactory.getSeesionFactory().getCurrentSession();
		 tx=session.beginTransaction();
		 session.update(s);
		 tx.commit();
		 return true;
		}catch(Exception e) {
			
			e.printStackTrace();
			tx.commit();
			return false;
		}finally {
			
			if(tx !=null) {
				tx=null;
			}
		}
	}

	@Override
	public boolean deleteStudents(String sid) {
		
		Transaction tx=null;
		try {
			
	     Session session=MyHibernateSessionFactory.getSeesionFactory().getCurrentSession();
		 tx=session.beginTransaction();
		 Students s=(Students) session.get(Students.class,sid);
		 session.delete(s);
		 tx.commit();
		 return true;
		}catch(Exception e) {
			e.printStackTrace();
			tx.commit();
			tx.rollback();
			return false;
		}finally {
			
			if(tx !=null) {
				tx=null;
			}
		}
	}

	public String getNewSid() {
		Transaction tx=null;
		try {
		Session session=MyHibernateSessionFactory.getSeesionFactory().getCurrentSession();
		tx=session.beginTransaction();
		Query query=session.createQuery("select max(sid) from Students");
		String sid=(String) query.uniqueResult();
		if(sid==null||"".equals(sid)) {
			//给一个默认的最大编号
			sid="S0000001";
		}else {
			//取后七位
			String temp=sid.substring(1);
			//转成数字
			int i=Integer.parseInt(temp);
			i++;
			//再还原为数字
			temp=String.valueOf(i);
			int len=temp.length();
	        	//凑够七位
			for(int j=0;j<7-len;j++) {
				temp="0"+temp;
			}
			sid="s"+temp;
		}
		tx.commit();
		return sid;
		
		}catch(Exception e) {
			e.printStackTrace();
			tx.commit();
			return null;
		}finally {
			
			if(tx !=null) {
				tx=null;
			}
		}
		
	}
}
