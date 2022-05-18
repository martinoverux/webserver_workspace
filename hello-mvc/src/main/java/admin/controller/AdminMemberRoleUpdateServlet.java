package admin.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.dto.Member;
import member.model.dto.MemberRole;
import member.model.service.MemberService;

/**
 * Servlet implementation class AdminMemberRoleUpdateServlet
 */
@WebServlet("/admin/memberRoleUpdate")
public class AdminMemberRoleUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String memberId = request.getParameter("memberId");
			String memberRole = request.getParameter("memberRole");
			Member member = memberService.findByMemberId(memberId);
			member.setMemberRole(MemberRole.valueOf(memberRole));
			
			int result = memberService.updateMemberRole(member);
			String msg = "회원권한을 성공적으로 수정했습니다."; 

			request.getSession().setAttribute("msg", msg); 
			response.sendRedirect(request.getContextPath() + "/admin/memberList");
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
