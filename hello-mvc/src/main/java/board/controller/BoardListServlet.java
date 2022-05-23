package board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.dto.Attachment;
import board.model.dto.Board;
import common.HelloMvcUtils;
import member.model.service.MemberService;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/board/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		int numPerPage = memberService.NUM_PER_PAGE;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch ( NumberFormatException e) {
			// 예외 발생 시 현재 페이지는 1로 처리
		}
		Map<String, Object> paramBorad = new HashMap<>();
        int start = (cPage - 1) * numPerPage + 1;
        int end =  cPage * numPerPage;
		
        paramBorad.put("start", start);
        paramBorad.put("end", end);
		
		// 2. 업무 로직
		// 2.a. content 영역
		List<Board> boardList = memberService.findAllBoard(paramBorad);
		List<Attachment> attachList = memberService.findAllBoardAttach();
		System.out.println("list = " + boardList);
		System.out.println("list = " + attachList);
		
		// 2.b. pagebar 영역
		int totalContentsBoard = memberService.getTotalContentsBoard(); 
		String url = request.getRequestURI();
		String pagebar = HelloMvcUtils.getPageBar(cPage, numPerPage, totalContentsBoard, url);
		System.out.println("pagebar = " + pagebar);
		
		// 3. view단 처리
		request.setAttribute("boardList", boardList);
		request.setAttribute("attachList", attachList);
		request.setAttribute("pagebar", pagebar);
		
		request.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp")
			   .forward(request, response);
	
	}

}
