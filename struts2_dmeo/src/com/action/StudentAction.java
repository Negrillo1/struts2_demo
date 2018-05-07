package com.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import utils.ExcelExportUtils;

import com.dao.StudentDao;
import com.dao.StudentDaoImpl;
import com.entity.Classes;
import com.entity.PageBean;
import com.entity.Student;
import com.opensymphony.xwork2.ActionContext;


public class StudentAction extends SuperAction{

	private ExcelExportUtils excelExportUtils = new ExcelExportUtils();
	private int id;
	private Student s;
	private Classes c = new Classes();
	private File file;
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public Student getS() {
		return s;
	}
	public void setS(Student s) {
		this.s = s;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
/*	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if("".equals(s.getSid())) {
			this.addFieldError("SidError","学号不能为空");
		}
		
	}*/
	/*
	 * 获取pc
	 */
	public int getPc(HttpServletRequest request) {
		String value = request.getParameter("pc");
		if(value == null || value.trim().isEmpty()) {
			return 1;
		} else {
			return Integer.parseInt(value);
		}
	}
	/*
	 * 截取url
	 */
	public String getUrl(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		String queryString = request.getQueryString();
		if(queryString.contains("&pc=")) {
			int index = queryString.lastIndexOf("&pc");
			queryString = queryString.substring(0,index);
		}
		System.out.println(queryString);
		return contextPath + servletPath + "?" + queryString;
	}
	/*
	 * 查询所有学生信息
	 */
	public String queryAll() {
		int pc = getPc(request);
		int ps = 3;
		StudentDao sdao = new StudentDaoImpl();
		PageBean<Student> pb = sdao.queryAll(pc,ps);
		session.setAttribute("pb", pb);
		
		return "query_success";
	}
	/*
	 * 按学号查询
	 */
	public String queryById() {
		StudentDao sdao = new StudentDaoImpl();
		Student s = sdao.queryById(id);
		if(s !=null) {
			session.setAttribute("queryById_list", s);
			return "queryById_success";
		} else {
			return "queryById_failure";
		}
		
	}
	/*
	 * 删除学生信息
	 */
	public String delete() {
		StudentDao sdao = new StudentDaoImpl();
		int sid = Integer.parseInt(request.getParameter("id").toString());
		if(sid > 0) {
			if(sdao.deleteStudent(sid)){
				return "delete_success";
			}
		}
		
		return "deletd_failure";
		
	}
	/*
	 * 添加学生信息
	 */
	public String add() {
		Student s = new Student();
		s.setSid(request.getParameter("sid"));
		s.setSname(request.getParameter("sname"));
		s.setSsex(request.getParameter("ssex"));
		s.setSage(request.getParameter("sage"));
		c.setId(request.getParameter("sclass"));
		s.setClasses(c);
		StudentDao sdao = new StudentDaoImpl();
		if(sdao.addStudent(s)) {
			return "add_success";
		}
		return "add_failure";
	}
	/*
	 * 更新——查询出成绩
	 */
	public String update1() {
		StudentDao sdao = new StudentDaoImpl();
		int sid = Integer.parseInt(request.getParameter("id").toString());
		Student s = sdao.queryById(sid);
		session.setAttribute("id", s.getId());
		if(s != null) {
			session.setAttribute("update1", s);
			return "update1_success";
		}
		return "update1_failure";
	}
	/*
	 * 更新——保存更改后的数据
	 */
	public String update2() {
		StudentDao sdao = new StudentDaoImpl();
		Student s = new Student();
		int id = Integer.parseInt(session.getAttribute("id").toString());
		s.setId(id);
		s.setSid(request.getParameter("sid"));
		s.setSname(request.getParameter("sname"));
		s.setSsex(request.getParameter("ssex"));
		s.setSage(request.getParameter("sage"));
		if(sdao.updateStudent(s)) {
			return "update2_success";
		}
		return "update2_failure";
	}
	/*
	 * 多条件查询
	 */
	public String queryByCon() throws Exception {
		response.setContentType("text/html;charset=utf-8");
		int pc = getPc(request);
		int ps = 3;
		StudentDao sdao = new StudentDaoImpl();
		Student s = new Student();
		s.setSid(request.getParameter("sid"));
		s.setSname(request.getParameter("sname"));
		s.setSsex(request.getParameter("ssex"));
		System.out.println(request.getParameter("ssex"));
		s.setSage(request.getParameter("sage"));
//		s = encoding(s);
		PageBean<Student> pb = sdao.query(s,pc,ps);
		pb.setUrl(getUrl(request));
		System.out.println(pb.getUrl());
		session.setAttribute("pb", pb);

		return "queryByCon_success";
	}
	/*
	 * get编码
	 */
	private Student encoding(Student s2) throws UnsupportedEncodingException {
		
		// TODO Auto-generated method stub
		
		String sid = s2.getSid();
		String sname = s2.getSname();
		String ssex = s2.getSsex();
		String sage = s2.getSage();
		
		
		if(sid != null && !sid.trim().isEmpty()) {
			sid = new String (sid.getBytes("ISO-8859-1"),"utf-8");
			s2.setSid(sid);
		}
		if(sname != null && !sname.trim().isEmpty()) {
			sname = new String (sname.getBytes("ISO-8859-1"),"utf-8");
			s2.setSname(sname);
		}
		if(ssex != null && !ssex.trim().isEmpty()) {
			ssex = new String (ssex.getBytes("ISO-8859-1"),"utf-8");
			s2.setSsex(ssex);
			System.out.println(ssex);
		}
		if(sage != null && !sage.trim().isEmpty()) {
			sage = new String (sage.getBytes("ISO-8859-1"),"utf-8");
			s2.setSage(sage);
		}
		return s2;
	}
	/**
	 * 导出当前页信息
	 * @throws Exception
	 */
	public void exportExcel() throws Exception {
		PageBean<Student> pb = (PageBean<Student>) session.getAttribute("pb");
		List<Student> list = pb.getBeanList();
		HSSFWorkbook wb = excelExportUtils.export(list);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=student.xls");
		OutputStream outputStream = response.getOutputStream();
		wb.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}
	/**
	 * 导出全部信息
	 * @throws Exception
	 */
	public void exportExcelAll() throws Exception {
		StudentDao sdao = new StudentDaoImpl();
		List<Student> list = sdao.queryAll();
		HSSFWorkbook wb = excelExportUtils.export(list);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=student.xls");
		OutputStream outputStream = response.getOutputStream();
		wb.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}
	/**
	 * 模板下载
	 * @throws Exception
	 */
	public void TempletDownload() throws Exception {
		HSSFWorkbook wb = excelExportUtils.export();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=student.xls");
		OutputStream outputStream = response.getOutputStream();
		wb.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}
	/**
	 * 导入excel到数据库
	 * @return
	 */
	public String importExcel() {
		StudentDao sdao = new StudentDaoImpl();
		//得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String savePath = ServletActionContext.getServletContext().getRealPath("/excel");
		String FileName = request.getParameter("FileName");
		String temp[] = FileName.split("\\\\");
		if(temp.length > 1) {
			//将反斜杠去除
			FileName = temp[temp.length - 1];
		}
		if(file!= null) {
			File savefile = new File(new File(savePath),FileName);
			if(!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			try {
				FileUtils.copyFile(file, savefile);
			} catch(IOException e) {
				e.printStackTrace();
			}
			ActionContext.getContext().put("message", "文件上传成功,正在导入Excel");
		}
		//获取文件路径
		String filePath = savePath + "\\" + FileName;
		try{
			ExcelExportUtils ec = new ExcelExportUtils();
			ec.importExcel(filePath);
			List<Student> importList = ec.readExcel();
			System.out.println("size:" + importList.size());
			Iterator<Student> it = importList.iterator();
			
			//获取数据库里的所有信息
			sdao = new StudentDaoImpl();
			List<Student> list = sdao.queryAll();
			boolean flag = false;
			while(it.hasNext()) {
				Student s = it.next();
				Student stu = new Student();
				//判断场站名是否有重复值，有就不导入，没有就导入
				if(s.getSid() != null) {
					Student ss;
					Iterator<Student> iterator = list.iterator();
					while(iterator.hasNext()) {
						ss = iterator.next();
						if(s.getSid().equals(ss.getSid())) {
							flag = false;
							break;
						} else {
							flag = true;
						}
					}
					//flag为true说明信息不存在，导入该条数据
					if(flag) {
						if(s.getSid() != null) 
						stu.setSid(s.getSid());
						if(s.getSname() != null)
						stu.setSname(s.getSname());
						if(s.getSsex() != null)
						stu.setSsex(s.getSsex());
						if(s.getSage() != null)
						stu.setSage(s.getSage());
/*						if(s.getSclass() != null)
						stu.setSclass(s.getSclass());*/
						sdao.addStudent(stu);
					}
				}
				
			} 
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "import_success";
	}
	public String AddStudent() {
		
		StudentDao dao = new StudentDaoImpl();
		List<Classes> list = dao.getClassList();
		session.setAttribute("c_name_list", list);
		return "AddStudent";
	}
}
