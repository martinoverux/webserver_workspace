package board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
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
 * Servlet implementation class BoardEnrollServlet
 */
@WebServlet("/board/boardEnroll")
public class BoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	/**
	 * 게시글 쓰기 폼 요청
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/board/boardEnroll.jsp")
				.forward(request, response);
	}

	/**
	 * 게시글 쓰기 등록 요청
	 * 
	 * - 게시글 등록 시 /board/boardList 리다이렉트
	 * 
	 * 파일 업로드 절차
	 * - 1. 제출폼에 enctype="multipart/form-data" 작성
	 * - 2. MultipartRequest 객체 생성 - 파일저장 완료
	 * 		- a. HttpServletRequest
	 * 		- b. saveDirectory
	 * 		- c. maxPostSize 최대 업로드 크기
	 * 		- d. encoding
	 * 		- e. FileRenamePolicy 객체
	 * - 3. 사용자 입력값 처리 - HttpServletRequest 가 아니라 MultipartRequest 에서 값을 가져오기
	 * - 4. 업무로직 - DB board, attachment 에 레코드 등록
	 * - 5. 리다이렉트 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 2. MultipartRequest 객체 생성
			// b. 파일 저장 경로
//			"C:\\Workspaces\\webserver_workspace\\hello-mvc\\src\\main\\webapp\\upload\\board"
			ServletContext application = getServletContext();
			String webRoot = application.getRealPath("/");
			// File.separator 운영체제별 경로 구분자 (window: \, max/linux: /)
			// String saveDirectory = webRoot + File.separator + "upload" + File.separator + "board";
			String saveDirectory = webRoot + "/upload/board";
			// c. 최대 파일크기 10MB
			int maxPostSize = 1024 * 1024 * 10;
			// d. 파일 인코딩
			String encoding = "utf-8";
			// e. 파일명 재지정 정책 객체
			// DefaultFileNamePolicy 파일명 중복 시 numbering 처리
//			FileRenamePolicy policy = new DefaultFileRenamePolicy();
			FileRenamePolicy policy = new HelloMvcFileRenamePolicy();
			
			MultipartRequest multiReq = 
					new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);

			// 3. 사용자 입력값 처리
			// 제목, 작성자, 첨부파일, 내용
			String title = multiReq.getParameter("title");
			String memberId = multiReq.getParameter("writer");
			String content= multiReq.getParameter("content");
			int attachedCnt = 0;
			File upFile1 = multiReq.getFile("upFile1");
			File upFile2 = multiReq.getFile("upFile2");
			
			
			// DTO 객체 생성
			BoardExt board = new BoardExt();
			board.setTitle(title);
			board.setMemberId(memberId);
			board.setContent(content);
			
			// 첨부한 파일이 하나라도 있는 경우
			if(upFile1 != null || upFile2 != null ) {				
				List<Attachment> attachments = new ArrayList<>();
				if(upFile1 != null) {
					attachments.add(getAttachment(multiReq, "upFile1"));
				}
				if(upFile2 != null) {
					attachments.add(getAttachment(multiReq, "upFile2"));
				}
				board.setAttachments(attachments);
			}
			System.out.println("board = " + board);
			
			// 3. 업무로직(db insert)
			int result = boardService.insertBoard(board);
			
			// 4. 리다이렉트(DML 처리인 경우 url을 변경해서 새로고침 오류를 방지한다.)
			response.sendRedirect(request.getContextPath() + "/board/boardList");
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private Attachment getAttachment(MultipartRequest multiReq, String name) {
		Attachment attach = new Attachment();
		String originalFileName = multiReq.getOriginalFileName(name); // 업로드한 파일명
		String renamedFileName = multiReq.getOriginalFileName(name);  // 저장된 파일명
		attach.setOriginalFileName(originalFileName);
		attach.setRenamedFileName(renamedFileName);
		return attach;
	}

}
