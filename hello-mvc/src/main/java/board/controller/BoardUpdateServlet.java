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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import board.model.dto.Attachment;
import board.model.dto.BoardExt;
import board.model.service.BoardService;
import common.HelloMvcFileRenamePolicy;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/board/boardUpdate")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	/**
	 * 수정 폼 요청
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		// 2. 업무 로직
		BoardExt board = boardService.findByNo(no);
		// 3. view단 처리
		request.setAttribute("board", board);
		request	.getRequestDispatcher("/WEB-INF/views/board/boardUpdate.jsp")
				.forward(request, response);
	}
	

	/**
	 * DB에 수정 요청
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String savingDirectory = getServletContext().getRealPath("/upload/board");
			int maxPostSize = 1024 * 1024 * 10;
			String encoding = "utf-8";
			FileRenamePolicy policy = new HelloMvcFileRenamePolicy();
			MultipartRequest multiReq = new MultipartRequest(request, savingDirectory, maxPostSize, encoding, policy);
			
			String title = multiReq.getParameter("title");
			String memberId = multiReq.getParameter("writer");
			String content = multiReq.getParameter("content");
			int boardNo = Integer.parseInt(multiReq.getParameter("boardNo"));
			String[] delFiles = multiReq.getParameterValues("delFile"); // 삭제하려는 첨부파일 pk
			
		
			
			BoardExt board = new BoardExt();
			board.setTitle(title);
			board.setMemberId(memberId);
			board.setContent(content);
			board.setNo(boardNo);
			
			File upFile1 = multiReq.getFile("upFile1");
			File upFile2 = multiReq.getFile("upFile2");
			
			if(upFile1 != null | upFile2 != null) {
				List<Attachment> attachments  = new ArrayList<>();
				if(upFile1 != null) {
					attachments.add(getAttachment(multiReq, "upFile1"));
				}
				if(upFile2 != null) {
					attachments.add(getAttachment(multiReq, "upFile2"));
				}
				board.setAttachments(attachments);
			}
			
			int result = boardService.updateBoard(board);
			System.out.println("result = " + result);
			
			// 첨부파일 삭제 처리
			if(delFiles != null) {
				for(String temp : delFiles) {
					int attachNo = Integer.parseInt(temp); // attachment pk
					Attachment attach = boardService.findAttachmentByNo(attachNo);
					// a. 파일 삭제
					File delFile = new File(savingDirectory, attach.getRenamedFilename());
					if(delFile.exists()) {
						delFile.delete();
					}
					// b. db 레코드 삭제
					result = boardService.deleteAttachment(attachNo);
					System.out.println("> " + attachNo + "번 첨부파일 삭제");
				}
			}
			
			
			request.setAttribute("msg", "게시글 수정 실패");
			response.sendRedirect(request.getContextPath() + "/board/boardView?no=" + board.getNo());		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	} 
	
	private Attachment getAttachment(MultipartRequest multiReq, String name) {
		Attachment attach = new Attachment();
		String originalFilename = multiReq.getOriginalFileName(name);
		String renamedFilename = multiReq.getFilesystemName(name);
		attach.setOriginalFilename(originalFilename);
		attach.setRenamedFilename(renamedFilename);
		return attach;
		
	}

}
