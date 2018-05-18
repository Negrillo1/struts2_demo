package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.db.HibernateUtils;
import com.entity.Classes;
import com.entity.PageBean;
import com.entity.Student;
@Repository(value = "sdao")
public class StudentDaoImpl implements StudentDao{
	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("all")
	@Transactional
	public List<Student> queryAll() {
		List<Student> list = (List<Student>) hibernateTemplate.find("from Student");			
		if(list != null && list.size() != 0) {
			return list;
		}
		return null;
	}
	@SuppressWarnings("all")
	@Transactional
	public PageBean<Student> queryAll(final int pc,final int ps) {
		// TODO 查询所有学生信息	
		PageBean<Student> pb = new PageBean<Student>();
		List<Student> list=null;
		
		pb.setPc(pc);
		pb.setPs(ps);
		
			//得到tr
		String hql = "select count(*) from Student";
		Long count = (Long)hibernateTemplate.find(hql).listIterator().next();
		int c = count.intValue();
		pb.setTr(c);
		final String hql2 = "from Student";
		list = hibernateTemplate.execute(new HibernateCallback(){
		public Object doInHibernate(Session session) throws HibernateException{
			Query query = session.createQuery(hql2);
			query.setFirstResult((pc - 1)*ps);
			query.setMaxResults(ps);
			List list = query.list();
			return list;
		}
	});
		pb.setBeanList(list); 
		return pb;
	}
	
	@Transactional
	public Student queryById(int id) {
		// TODO 按照学号查询
	
		String hql = "from Student where id = ?";
		Student s = hibernateTemplate.get(Student.class, id);
		if(s !=null) {
			return s;
		}
		return null;
	}
	@Transactional
	public boolean deleteStudent(int id) {
		// TODO 删除学生信息
			Student s = hibernateTemplate.get(Student.class, id);
			if(s != null) {
				hibernateTemplate.delete(s);
				return true;
			}
		return false;
	}
	@Transactional
	public boolean addStudent(Student s) {
		// TODO 添加学生信息
			hibernateTemplate.save(s);
			return true;
	}
	@Transactional
	public boolean updateStudent(Student s) {
		// TODO 更新学生信息	
			hibernateTemplate.update(s);
			return true;
	}
	@SuppressWarnings("all")
	@Transactional
	public PageBean<Student> query(Student s ,final int pc,final int ps) {
		// TODO Auto-generated method stub
		final ArrayList<String> tiaojian = new ArrayList<String>();
		ArrayList<String> tiaojian2 = new ArrayList<String>();
		List<Student> list = new ArrayList<Student>();
		PageBean<Student> pb = new PageBean<Student>();
		int i = 0;
		int k = 0;
		pb.setPc(pc);
		pb.setPs(ps);
			final StringBuilder hql = new StringBuilder("FROM Student WHERE 1=1 ");
			StringBuilder hql2 = new StringBuilder("SELECT count(*) from Student where 1=1 ");
			if(s.getSid() != null && !s.getSid().trim().equals("")) {
				hql.append("AND sid = ?");
				hql2.append("AND sid = ?");
			}
			if(s.getSname() != null && !s.getSname().trim().equals("")) {
				hql.append("AND sname = ?");
				hql2.append("AND sname = ?");
			}
			if(s.getSsex() !=null && !s.getSsex().trim().equals("")) {
				hql.append("AND ssex = ?");
				hql2.append("AND ssex = ?");
				
			}
			if(s.getSage() !=null && !s.getSage().trim().equals("")) {
				hql.append("AND sage = ?");
				hql2.append("AND sage = ?");
			}
			if(s.getClasses().getId() !=null && !s.getClasses().getId().trim().equals("")) {
				hql.append("AND class_fk = ?");
				hql2.append("AND class_fk = ?");
			}

			
			
			if(s.getSid() != null && !s.getSid().trim().equals("")) {
				tiaojian.add(s.getSid());
			}
			if(s.getSname() != null && !s.getSname().trim().equals("")) {
				tiaojian.add(s.getSname());
				
//				query2.setParameter(k++, s.getSname());	
			}
			if(s.getSsex() !=null && !s.getSsex().trim().equals("")) {
				tiaojian.add(s.getSsex());
				
//				query2.setParameter(k++, s.getSsex());	
			}
			if(s.getSage() !=null && !s.getSage().trim().equals("")) {
				tiaojian.add(s.getSage());
				
//				query2.setParameter(k++, s.getSage());				
			}
			if(s.getClasses().getId() !=null && !s.getClasses().getId().trim().equals("")) {
				tiaojian.add(s.getClasses().getId());
//				query2.setParameter(k++, s.getClasses().getId());
			}
			int size = tiaojian.size();
			String[] tj = tiaojian.toArray(new String[size]);
			Long count = (Long) hibernateTemplate.find(hql2.toString(),tj).listIterator().next();
			int N = new Long(count).intValue();
			pb.setTr(N);
			list = hibernateTemplate.execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException {
							Query query = session.createQuery(hql.toString());
							for(int i = 0; i< tiaojian.size(); i++) {
								query.setParameter(i,tiaojian.get(i).toString());
							}
							query.setFirstResult((pc - 1)*ps);
							query.setMaxResults(ps);
							List list = query.list();
							return list;
				}
			});
			pb.setBeanList(list);
		return pb;
	}
	@SuppressWarnings("all")
	@Transactional
	public List<Classes> getClassList() {
		List<Classes> list = null;
		
		String hql = "from Classes c";
		list = (List<Classes>) hibernateTemplate.find(hql);
		if(list != null && list.size() != 0) {
			return list;
		}
		return null;
	}
}
