package com.kh.ajax.celeb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.ajax.celeb.dto.Celeb;
import com.kh.ajax.celeb.manager.CelebManager;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/celeb/deleteCeleb")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 2. 업무로직
		CelebManager manager = CelebManager.getInstance();
		List<Celeb> celebList = manager.getCelebList();
		for(int i = 0; i < celebList.size(); i++) {
			if(celebList.get(i).getNo() == no) {
				System.out.println("no = " + no);
				System.out.println("celebList.get(i) = " + celebList.get(i));
				celebList.remove(i);
				break;
			}
		}
		
		// 3. 응답처리 -  json 변환해서 출력
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(celebList, response.getWriter());
	}

}
