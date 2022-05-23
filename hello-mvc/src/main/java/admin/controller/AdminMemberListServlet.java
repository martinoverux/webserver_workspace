package admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.HelloMvcUtils;
import member.model.dto.Member;
import member.model.service.MemberService;

/**
 * 1. content 영역
 * 	- cPage 현재 페이지
 * 	- numPerPage 한 페이지당 표시할 콘텐츠수
 * 	- 페이징 쿼리
 * 		- start
 * 		- end
 * 
 * 2. pagebar 영역
 *  - totalContents 전체 콘텐츠수
 *  - totalpages 전체페이지수 12
 *  - pagebarSize 페이지바 길이 10
 *  - pageNo 페이지 증감변수
 *  - pagebarStart ~ pagebarEnd 페이지바 범위
 *  - url 다음요청url
 */
@WebServlet("/admin/memberList")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * select * from member order by enroll_date desc
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자 입력값 처리
			int numPerPage = memberService.NUM_PER_PAGE;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch ( NumberFormatException e) {
				// 예외 발생 시 현재 페이지는 1로 처리
			}
			Map<String, Object> param = new HashMap<>();
            int start = (cPage - 1) * numPerPage + 1;
            int end =  cPage * numPerPage;
			
			param.put("start", start);
			param.put("end", end);
			
			// 2. 업무 로직
			// 2.a. content 영역
			List<Member> list = memberService.findAll(param);
			System.out.println("list = " + list);
			
			// 2.b. pagebar 영역
			int totalContentsBoard = memberService.getTotalContents(); // select count(*) from member
			String url = request.getRequestURI();
			String pagebar = HelloMvcUtils.getPageBar(cPage, numPerPage, totalContentsBoard, url);
			System.out.println("pagebar = " + pagebar);
			
			// 3. view단 처리
			request.setAttribute("list", list);
			request.setAttribute("pagebar", pagebar);
			request
					.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp")
					.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}