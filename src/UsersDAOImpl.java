package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.imooc.entity.Users;

import db.MyHibernateSessionFactory;
import service.UsersDAO;

public class UsersDAOImpl implements UsersDAO {

	@Override
	public boolean usersLogin(Users u) {
		//事物对象
		Transaction tx=null;
		String hql="";
		try {
			
			Session session=MyHibernateSessionFactory.getSeesionFactory().getCurrentSession();
			//启动事物
			tx=session.beginTransaction();
			hql="from Users where username=? and password=? ";
		    Query query=session.createQuery(hql);
		    query.setParameter(0, u.getUsername());
		    query.setParameter(1, u.getPassword());
		    List list=query.list();
		    //提交事物 
		    tx.commit();
		    if(list.size()>0) {
		    	
		    	return true;
		    }else {
		    	return false;
		    }
		}catch(Exception ex) {
			
			ex.printStackTrace();
			return false;
		}finally {
			
			if(tx!=null) {
				//tx.commit();
				tx=null;
			}
		}
		
	}

}
