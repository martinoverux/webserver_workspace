package com.kh.ajax.celeb.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.ajax.celeb.dto.Celeb;
import com.kh.ajax.celeb.dto.CelebType;
import com.kh.ajax.celeb.manager.CelebManager;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/celeb/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. MultipartRequest 객체 생성( 파일 업로드 완료)
		String saveDirectory = getServletContext().getRealPath("/images");
		int maxPostSize = 1024 * 1024 * 10; 
		String encoding = "utf-8";
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
		// 2. 사용자 입력값 처리
		int no = Integer.parseInt(multiReq.getParameter("no"));
		String name = multiReq.getParameter("name");
		CelebType type = CelebType.valueOf(multiReq.getParameter("type"));
		String profile = "default.png";
		if(multiReq.getFile("profile") != null) {
			profile = multiReq.getFilesystemName("profile"); // 저장된 파일명
		}
		
		// 3. 업무로직
		Celeb updatedCeleb = null;
		for(Celeb celeb : CelebManager.getInstance().getCelebList()) {
			if(celeb.getNo() == no) {
				celeb.setName(name);
				celeb.setType(type);
				celeb.setProfile(profile);
				updatedCeleb = celeb;
				break;
			}
		}
		// 4. 응답처리
		response.setContentType("application/json; charset=utf-8");
		Map<String, Object> map = new HashMap<>();
		new Gson().toJson(map, response.getWriter());
	}
}