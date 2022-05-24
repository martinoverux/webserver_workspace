package board.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import board.model.dto.Attachment;
import board.model.dto.BoardExt;
import board.model.dao.BoardDao;

public class BoardService {
	
	public static final int NUM_PER_PAGE = 10; // 한 페이지에 표시할 콘텐츠 수
	private BoardDao boardDao = new BoardDao();
	
	
	public List<BoardExt> findAllBoard(Map<String, Object> paramBorad) {
		Connection conn = getConnection();
		List<BoardExt> boardList = boardDao.findAllBoard(conn, paramBorad);
		close(conn);
		return boardList;
	}

	public int getTotalContentsBoard() {
		Connection conn = getConnection();
		int totalContentsBoard = boardDao.getTotalContentsBoard(conn);
		close(conn);
		return totalContentsBoard;
	}

	public List<Attachment> findAllBoardAttach() {
		Connection conn = getConnection();
		List<Attachment> attachList = boardDao.findAllBoardAttach(conn);
		close(conn);
		return attachList;
	}

	public int insertBoard(BoardExt board) {
		int result = 0;
		Connection conn = getConnection();
		try {
			// 1. board에 등록
			result = boardDao.insertBoard(conn, board); // pk no값 결정 - seq_board_no.nextval
			
			// 2. board pk 가져오기
			int no = boardDao.findCurrentBoardNo(conn); // seq_board_no.currval
			System.out.println("방금 등록된 board.no = " + no);
			// 3. attachment에 등록
			List<Attachment> attachments = board.getAttachments(); 
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					attach.setBoardNo(no);
					result = boardDao.insertAttachment(conn, attach);
				}
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;  
		} finally {
			close(conn);
		}
		return result;
	}

}