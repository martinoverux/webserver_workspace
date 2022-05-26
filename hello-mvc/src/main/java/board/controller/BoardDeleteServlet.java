package board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.dto.Attachment;
import board.model.service.BoardService;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/board/boardDelete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자 입력값 처리
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			
			//2. 서비스로직호출
			//2.1. 첨부파일 삭제
			String deleteDirectory = getServletContext().getRealPath("/upload/board");
			List<Attachment> attachments = boardService.findAttachmentByBoardNo(boardNo);
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					File deleteFile = new File(deleteDirectory, attach.getRenamedFilename());
					deleteFile.delete();				
				}
			}
			
			//2.2. 게시글 삭제
			int result = boardService.deleteBoard(boardNo);
			
			//3. 리다이렉트 처리
			HttpSession session = request.getSession();
			session.setAttribute("msg", "게시글 삭제가 성공적으로 처리되었습니다.");
			response.sendRedirect(request.getContextPath() + "/board/boardList");			
			
		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
