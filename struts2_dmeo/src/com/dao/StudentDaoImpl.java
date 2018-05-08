package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.db.HibernateUtils;
import com.entity.Classes;
import com.entity.PageBean;
import com.entity.Student;

public class StudentDaoImpl implements StudentDao{

	public List<Student> queryAll() {
		List<Student> list = null;
		Transaction tx = null;
		try{
			Session session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			String hql = "from Student";
			Query query = session.createQuery(hql);
			list = query.list();
			tx.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public PageBean<Student> queryAll(int pc,int ps) {
		// TODO 查询所有学生信息		
		PageBean<Student> pb = new PageBean<Student>();
		List<Student> list=null;
		Transaction tx = null;
		try {
			Session session = HibernateUtils.getSession();
			pb.setPc(pc);
			pb.setPs(ps);
			tx = session.beginTransaction();
			//得到tr
			String hql = "select count(*) from Student";
			Long n = (Long) session.createQuery(hql).uniqueResult();
			int N = new Long(n).intValue();
			pb.setTr(N);
			hql = "from Student";
			Query query2 = session.createQuery(hql);
			list = query2.setFirstResult((pc - 1)*ps).setMaxResults(ps).list();
			pb.setBeanList(list);
			tx.commit();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (tx != null) {
				tx = null;
			}
		}
		return pb;
	}

	public Student queryById(int id) {
		// TODO 按照学号查询
		Transaction tx = null;
		Student s = new Student();
		try {
			Session session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			s = session.get(Student.class, id);
			tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (tx != null) {
				tx = null;
			}
		}
		return s;
	}

	public boolean deleteStudent(int id) {
		// TODO 删除学生信息
		Transaction tx = null;
		try{
			Session session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			Student s = session.get(Student.class, id);
			if(s != null) {
				session.delete(s);
				tx.commit();
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (tx != null) {
				tx = null;
			}
		}
		return false;
	}

	public boolean addStudent(Student s) {
		// TODO 添加学生信息
		Transaction tx = null;
		try{
			Session session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			session.save(s);
			tx.commit();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (tx != null) {
				tx = null;
			}
		}
		return false;
	}

	public boolean updateStudent(Student s) {
		// TODO 更新学生信息
		Transaction tx = null;
		try{
			Session session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			session.update(s);
			tx.commit();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (tx != null) {
				tx = null;
			}
		}
		return false;
	}

	public PageBean<Student> query(Student s ,int pc,int ps) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		List<Student> list = new ArrayList<Student>();
		PageBean<Student> pb = new PageBean<Student>();
		int i = 0;
		int k = 0;
		pb.setPc(pc);
		pb.setPs(ps);
		try {
			Session session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			StringBuilder hql = new StringBuilder("FROM Student WHERE 1=1 ");
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

			Query query = session.createQuery(hql.toString());
			Query query2 = session.createQuery(hql2.toString());
			if(s.getSid() != null && !s.getSid().trim().equals("")) {
				query.setParameter(i++,s.getSid());
				query2.setParameter(k++, s.getSid());
			}
			if(s.getSname() != null && !s.getSname().trim().equals("")) {
				query.setParameter(i++,s.getSname());
				query2.setParameter(k++, s.getSname());	
			}
			if(s.getSsex() !=null && !s.getSsex().trim().equals("")) {
				query.setParameter(i++,s.getSsex());
				query2.setParameter(k++, s.getSsex());	
			}
			if(s.getSage() !=null && !s.getSage().trim().equals("")) {
				query.setParameter(i++,s.getSage());
				query2.setParameter(k++, s.getSage());				
			}
			if(s.getClasses().getId() !=null && !s.getClasses().getId().trim().equals("")) {
				query.setParameter(i++, s.getClasses().getId());
				query2.setParameter(k++, s.getClasses().getId());
			}

			Long n = (Long)query2.uniqueResult();
			int N = new Long(n).intValue();
			pb.setTr(N);
			list = query.setFirstResult((pc - 1)*ps).setMaxResults(ps).list();
			tx.commit();
			pb.setBeanList(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (tx != null) {
				tx = null;
			}
		}
		return pb;
	}
	public List<Classes> getClassList() {
		Transaction tx = null;
		List<Classes> list = null;
		
		try{
			Session session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			String hql = "from Classes c";
			Query query = session.createQuery(hql);
			list = query.list();
			tx.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if(tx != null) {
				tx = null;
			}
		}
		return list;
	}
}
