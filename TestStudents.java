package entity;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import com.imooc.entity.Students;

public class TestStudents {
	@Test
	public void testSchemaExport() {

		// 创建配置对象
		Configuration config = new Configuration().configure();
		// 创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		// 创建sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		// 创建session对象
		Session session = sessionFactory.getCurrentSession();
		// 创建SchemaExport对象
		SchemaExport export = new SchemaExport(config);

		export.create(true, true);
	}

	@Test
	public void testAdd() {

		// 创建配置对象
		Configuration config = new Configuration().configure();
		// 创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		// 创建sessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		// 创建session对象
		Session session = sessionFactory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		
		Students s1=new Students("s0000001","郑继光","男",new Date(),"安徽");
		Students s2=new Students("s0000002","陈超","男",new Date(),"安徽");
		Students s3=new Students("s0000003","张帅","男",new Date(),"河南");
		
		session.save(s1);
		session.save(s2);
		session.save(s3);
		
	    tx.commit();
	    sessionFactory.close();
	}
}
