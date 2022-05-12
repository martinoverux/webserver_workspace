package com.kh.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestPersonServlet3
 */
@WebServlet("/menuOrder.do")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String mainMenu = request.getParameter("mainMenu");
		String sideMenu = request.getParameter("sideMenu");
		String drinkMenu = request.getParameter("drinkMenu");
		
		int priceSum = 0;
		
		// 메인메뉴 금액 계산
		switch(mainMenu) {
		case "한우버거": 
			priceSum += 5000; 
			break;
		case "밥버거": 
			priceSum += 4500;
			break;
		case "치즈버거": 
			priceSum += 4000; 
			break;
		}
		
		// 사이드 메뉴 금액 계산
		switch(sideMenu) {
		case "감자튀김": 
			priceSum += 1500; 
			break;
		case "어니언링": 
			priceSum += 1700; 
			break;
		}
		
		// 음료 메뉴 금액 계산
		switch(drinkMenu) {
		case "콜라": 
			priceSum += 1000; 
			break;
		case "사이다": 
			priceSum += 1000; 
			break;
		case "커피": 
			priceSum += 1500;
			break;
		case "밀크쉐이크": 
			priceSum += 2500;
			break;
		}

		request.setAttribute("priceSum", priceSum);
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/menu/menuEnd.jsp");
		reqDispatcher.forward(request, response);
	}

}
