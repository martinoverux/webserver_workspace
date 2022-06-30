package com.kh.student.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.student.model.dto.Student;
import com.kh.student.model.service.StudentService;

public class StudentController extends AbstractController {
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		int no = 0;
		try {
			no = Integer.parseInt(request.getParameter("no"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		// 2. 업무로직 (1명 조회)
		if (no != 0) {
			// dto 처리
			Student student = studentService.selectOne(no);
			System.out.println("student = " + student);

			// map<컬럼명, 컬럼값>
			
			 Map<String, Object> studentMap = studentService.selectOneMap(no);
			 System.out.println("studentMap = " + studentMap);
		
			request.setAttribute("student", studentMap);
		}
		return "student/student";
	}
}
