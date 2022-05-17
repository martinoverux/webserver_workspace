package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.dto.Member;
import member.model.service.MemberService;

/**
 * Servlet implementation class DeleteMemberServlet
 */
@WebServlet("/member/deleteMember")
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("loginMember");
		session.setAttribute("member", member);

		request
			.getRequestDispatcher("/WEB-INF/views/member/deleteMember.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				HttpSession session = request.getSession();
				Member member = (Member) session.getAttribute("member");

				int result = memberService.deleteMember(member);	
				
				if(session != null) {
					session.invalidate();			
				}
				
				String msg = null;
				if(result == 1) {
				  msg =	"회원 탈퇴가 정상적으로 이뤄졌습니다.";				
				}
				request.getSession().setAttribute("msg", msg); // redirect 후에 꺼내서 출력
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>opener.parent.location.reload(); window.close();</script>");
				out.flush();
	}		
}