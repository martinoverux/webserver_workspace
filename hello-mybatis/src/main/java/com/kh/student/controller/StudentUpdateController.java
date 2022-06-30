package com.kh.student.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.student.model.dto.Student;
import com.kh.student.model.service.StudentService;

public class StudentUpdateController extends AbstractController {
	private StudentService studentService;
	
	public StudentUpdateController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		int no = 0;
		try {
			no = Integer.parseInt(request.getParameter("no"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		
		// 2. 업무로직 (1명 조회)
		Student student = new Student(no, name, tel, null, null, null);
		Map<String, Object> studentMap;
		if (no != 0) {
			 int result = studentService.updateStudent(student);
			 System.out.println("result = " + result);
			 if(result > 0 ) {
				 studentMap = studentService.selectOneMap(no);
				 request.setAttribute("student", studentMap);
			 }
		}
		return "redirect:/student/student.do";
	}
}
