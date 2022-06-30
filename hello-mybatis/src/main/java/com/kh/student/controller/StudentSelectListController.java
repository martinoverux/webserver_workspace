package com.kh.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.student.model.service.StudentService;

public class StudentSelectListController extends AbstractController {
	private StudentService studentService;

	public StudentSelectListController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	
	
	
}
