package com.dao;

import java.util.List;

import com.entity.PageBean;
import com.entity.Student;

public interface StudentDao {

	public PageBean<Student> queryAll(int pc,int ps);
	public List<Student> queryAll();
	public Student queryById(int id);
	public boolean deleteStudent(int id);
	public boolean updateStudent(Student s);
	public boolean addStudent(Student s);
	public PageBean<Student> query(Student s,int pc,int ps);
}
