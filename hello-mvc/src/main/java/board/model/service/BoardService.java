package board.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.getConnection;

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
}
