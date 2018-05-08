package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.entity.Classes;
import com.entity.Student;

public class ExcelExportUtils{

	private XSSFWorkbook xssfWB = null;
	private HSSFWorkbook hssfWB = null;
	InputStream read = null;
	OutputStream write = null;
	private String file;
	String[] excelHeader = {"sid", "sname", "ssex","sage","sclass"};
	public  HSSFWorkbook export(List<Student> list) {
		HSSFWorkbook wb = new HSSFWorkbook();  
		HSSFSheet sheet = wb.createSheet("Student");  
		HSSFRow row = sheet.createRow((int) 0);  
		HSSFCellStyle style = wb.createCellStyle();  
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
		   
		for(int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);  
		    cell.setCellValue(excelHeader[i]);  
		    cell.setCellStyle(style);  
		    sheet.autoSizeColumn(i);
		    sheet.setColumnWidth(i, 4500);
		    }  
		   
		for(int i = 0; i < list.size(); i++) {  
		    row = sheet.createRow(i + 1);  
		    Student student = list.get(i);  
		    row.createCell(0).setCellValue(student.getSid());  
		    row.createCell(1).setCellValue(student.getSname());  
		    row.createCell(2).setCellValue(student.getSsex());
		    row.createCell(3).setCellValue(student.getSage());
		    row.createCell(4).setCellValue(student.getClasses().getC_name());
		    }
		return wb;       
	}
	public HSSFWorkbook export() {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("templet");
		HSSFRow row = sheet.createRow((int) 0);  
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		for(int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);  
		    cell.setCellValue(excelHeader[i]);  
		    cell.setCellStyle(style);  
		    sheet.autoSizeColumn(i);
		    sheet.setColumnWidth(i, 4500);
		}
		return wb;
	}
	public void importExcel(String file) throws Exception {
		
		this.file = file;
		read = null;
		if(file == null || "".equals(file)) {
			System.out.println("文件不能为空");
			throw new Exception();
	}
		try {
			read = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				System.out.println("文件路径不正确");
				e.printStackTrace();
			}
		try {
			if(file.endsWith(".xls")){
                hssfWB = new HSSFWorkbook(read);
            }else if(file.endsWith(".xlsx")){
                xssfWB = new XSSFWorkbook(read);
            }else{
                System.out.println("文件格式不正确!");
                throw new IOException();
            }
		} catch (IOException e) {
			System.out.println("文件已损坏或者未知原因!");
			e.printStackTrace();
		}
		}
	public List<Student> readExcel() {
		List<Student> list = null;
		if(xssfWB != null) {
			list = this.read2007Excel();
		}/* else if(hssfWB != null) {
			list = this.read2003Excel();
		}*/
		this.free();
		return list;
	}
	/**
	 * 释放IO流
	 * 减少内存损耗
	 */
	public void free() {
		try{
			if(read != null) {
				read.close();
			}
			if(write != null) {
				write.close();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 私有化读取2007以上版本Excel
	 */
	public List<Student> read2007Excel() {
		List<Student> list = new ArrayList<Student>();
		XSSFSheet sheet = xssfWB.getSheetAt(0);
		XSSFRow row;
		Student s = new Student();
		Classes c = new Classes(); 
		if(sheet.getPhysicalNumberOfRows() > 0) {
			//从Excel表格第1行开始读取
			for(int i = 1; i<sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				System.out.println("row:"+row.getRowNum());
				if(row == null)
					continue;
				XSSFCell Sid= row.getCell(0);
				XSSFCell Sname = row.getCell(1);
				XSSFCell Ssex = row.getCell(2);
				XSSFCell Sage = row.getCell(3);
				XSSFCell Sclass = row.getCell(4);
				System.out.println("cellType:"+Sid.getCellType());
				System.out.println("cellType:"+Sname.getCellType());
				System.out.println("cellType:"+Ssex.getCellType());
				System.out.println("cellType:"+Sage.getCellType());
				System.out.println("cellType:"+Sclass.getCellType());
				String sid = (String)getCellValue(Sid);
				String sname = (String)getCellValue(Sname);
				String ssex = (String)getCellValue(Ssex);
				String sage = (String)getCellValue(Sage);
				String sclass = (String)getCellValue(Sclass);
				System.out.println("catalog:"+sid);
				System.out.println("catalog:"+sname);
				System.out.println("catalog:"+ssex);
				System.out.println("catalog:"+sage);
				System.out.println("catalog:"+sclass);
				s.setSid(sid);
				s.setSname(sname);
				s.setSsex(ssex);
				s.setSage(sage);
				c.setC_name(sclass);
				s.setClasses(c);
				list.add(s);
			}
		}else {
			System.out.println("文件中没有数据!");
		}
		return list;
	}
	public static Object getCellValue (Cell cell) {
		Object obj = null;
		if(cell == null) {
			return null;
		}
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			obj = cell.getRichStringCellValue().getString().trim();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) { 
                obj = cell.getDateCellValue();
            }else { 
                NumberFormat nf = NumberFormat.getInstance();
                nf.setGroupingUsed(false);
                obj = nf.format(cell.getNumericCellValue()); 
            }
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			obj = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA:
			obj = cell.getCellFormula();
			break;
			default:
		}
		return obj;
	}
	
}
