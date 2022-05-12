package com.kh.web.servlet;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @WebServlet("/ServletLifeCycle")
 * - web.xml에서 servlet, servlet-mapping를 통해 특정url에 특정servlet 등록하는일을 대신 처리
 * - url-pattern을 작성. 반드시 /로 시작할것(contenxt-path는 생략)
 * 
 * Servlet 생명주기
 * - 생성자 호출
 * - @PostConstruct 메소드 호출
 * - init(ServletConfig) 호출
 * 
 * - 실제요청시 GenericServlet#service 호출 - 전송방식별 분기
 * - doGet | doPost 호출
 * 
 * - destroy 호출
 * - @PreDestroy 호출 
 * 
 */
@WebServlet("/ServletLifeCycle")
public class ServletLifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLifeCycle() {
       System.out.println("> ServletLifeCycle 생성자   호출!!");
    }
    
    @PostConstruct
    public void postContruct() {
    	System.out.println("> ServletLifeCycle @PostConstruct메소드 호출");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("> ServletLifeCycle#init 메소드 호출 : " + config);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("> ServletLifeCycle#doGet 메소드 호출");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("> ServletLifeCycle#destroy 메소드 호출");
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("> ServletLifeCycle @preDestroy 메소드 호출");
	}


}
