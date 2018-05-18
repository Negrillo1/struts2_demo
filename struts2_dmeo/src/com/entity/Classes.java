package com.entity;

import java.util.Set;

import org.springframework.stereotype.Repository;
@Repository
public class Classes {

	private String id;
	private String c_name;
	private Set<Student> students;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
}
