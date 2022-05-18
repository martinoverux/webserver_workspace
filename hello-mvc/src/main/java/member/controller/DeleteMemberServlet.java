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
	
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
//			//1. 사용자 입력값 처리
//			String memberId = request.getParameter("memberId");
//			
//			//2. 서비스로직호출
//			int result = memberService.deleteMember(memberId);
//			
//			// 탈퇴후처리 - 세션폐기, 쿠키폐기
//			Cookie cookie = new Cookie("saveId", memberId);
//			cookie.setPath(request.getContextPath());
//			cookie.setMaxAge(0); // 응답을 받은 즉시 삭제
//			response.addCookie(cookie);
//			
//			// 모든 세션속성 제거 (session.invalidate() 대신)
//			HttpSession session = request.getSession();
//			Enumeration<String> names = session.getAttributeNames(); 
//			while(names.hasMoreElements()) {
//				String name = names.nextElement();
//				session.removeAttribute(name);
//			}
//			
//			//3. 리다이렉트 처리
//			session.setAttribute("msg", "탈퇴가 성공적으로 처리되었습니다. 감사합니다.");
//			response.sendRedirect(request.getContextPath() + "/");
////			response.sendRedirect(request.getContextPath() + "/member/logout");
//		}
}