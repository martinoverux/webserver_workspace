package com.kh.ajax.jquery;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ajax.celeb.dto.Celeb;
import com.kh.ajax.celeb.dto.CelebType;
import com.kh.ajax.celeb.manager.CelebManager;

/**
 * Servlet implementation class CsvServlet
 */
@WebServlet("/jquery/csv")
public class CsvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			// 1. 사용자 입력값
			
			// 2. 업무로직
			CelebManager manager = CelebManager.getInstance();
			List<Celeb> celebList = manager.getCelebList();
			
			// 3. view단 처리
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			String actor = "<option value='CelebType.ACTOR'>배우</option>";
			String singer = "<option value='CelebType.SINGER'>가수</option>";
			String model = "<option value='CelebType.MODEL'>모델</option>";
			String comedian = "<option value='CelebType.COMEDIAN'>코미디언</option>";
			String entertainer = "<option value='CelebType.ENTERTAINER'>엔터테이너</option>";
			
			for(Celeb celeb : celebList) {
//				out.println(celeb); // celeb.tostring() + "\n"
				out.append("<tr>");
				out.append("<td>"+ celeb.getNo() + "</td>");
				out.append("<td>"+ celeb.getName() + "</td>");
				out.append("<td><select>");

				switch(celeb.getType()) {
				case ACTOR : out.append("<option value='CelebType.ACTOR' selected>배우</option>" + singer + model + comedian + entertainer); break;
				case SINGER : out.append(actor +"<option value='CelebType.SINGER' selected>가수</option>" + model + comedian + entertainer); break;
				case MODEL : out.append(actor + singer + "<option value='CelebType.MODEL' selected>모델</option>" + comedian + entertainer); break;
				case COMEDIAN : out.append(actor + singer +  model + "<option value='CelebType.COMEDIAN' selected>코미디언</option>"  + entertainer); break;
				case ENTERTAINER : out.append(actor + singer +  model + comedian +"<option value='CelebType.ENTERTAINER' selected>엔터테이너</option>"); break;
				}			
				
				out.append("</select></td>");
				out.append("<td>"+"<img src='"+ request.getContextPath() +"/images/"+ celeb.getProfile() + "'></td>");
				out.append("</tr>");
			}
	}
}