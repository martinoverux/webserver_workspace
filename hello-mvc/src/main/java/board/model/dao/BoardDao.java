package board.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import board.model.dto.Attachment;
import board.model.dto.BoardExt;
import board.model.dao.BoardDao;
import board.model.exception.BoardException;
import member.model.exception.MemberException;

public class BoardDao {
	
	private Properties prop = new Properties();
	
	public BoardDao() {
		String fileName = BoardDao.class.getResource("/sql/board-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private BoardExt handleBoardResultSet(ResultSet rset) throws SQLException {
		BoardExt board;
		board = new BoardExt();

		board.setNo(rset.getInt("no"));
		board.setTitle(rset.getString("title"));
		board.setMemberId(rset.getString("member_id"));
		board.setContent(rset.getString("content"));
		board.setReadCount(rset.getInt("read_Count"));
		board.setRegDate(rset.getDate("reg_date"));
		board.setAttachCount(rset.getInt("attach_cnt"));
		return board;
	}
	
	private Attachment handleAttachmentResultSet(ResultSet rset) throws SQLException {
		Attachment attachment;
		attachment = new Attachment();

		attachment.setNo(rset.getInt("no"));
		attachment.setBoardNo(rset.getInt("board_no"));
		attachment.setOriginalFileName(rset.getString("original_filename"));
		attachment.setRenamedFileName(rset.getString("renamed_filename"));
		attachment.setRegDate(rset.getDate("reg_date"));
		return attachment;
	}
	
	public List<BoardExt> findAllBoard(Connection conn, Map<String, Object> paramBorad) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<BoardExt> boardList = new ArrayList<>();
		String sql = prop.getProperty("findAllBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) paramBorad.get("start"));
			pstmt.setInt(2, (int) paramBorad.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BoardExt board = handleBoardResultSet(rset);
				boardList.add(board);
			}
		} catch (Exception e) {
			throw new BoardException("게시판 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return boardList;
	}

	public int getTotalContentsBoard(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int getTotalContentsBoard = 0;
		String sql = prop.getProperty("getTotalContentsBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				getTotalContentsBoard = rset.getInt(1); // 컬럼 인덱스
			}
		} catch (Exception e) {
			throw new BoardException("전체 게시글 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return getTotalContentsBoard;
	}

	public List<Attachment> findAllBoardAttach(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> attachList = new ArrayList<>();
		String sql = prop.getProperty("findAllBoardAttach");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Attachment attachment = handleAttachmentResultSet(rset);
				attachList.add(attachment);
			}
		} catch (Exception e) {
			throw new BoardException("첨부파일 포함 게시글 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return attachList;
	}

	public int insertBoard(Connection conn, BoardExt board) {
		String sql = prop.getProperty("insertBoard");
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getMemberId());
			pstmt.setString(3, board.getContent());	
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new BoardException("게시글 등록 오류", e);
		}finally {
			close(pstmt);
		}
		return result;
	}


	public int findCurrentBoardNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int no = 0;
		String sql = prop.getProperty("findCurrentBoardNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				no = rset.getInt(1);
			}
		} catch (SQLException e) {
			 throw new BoardException("보드 번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return no;
	}

	public int insertAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getBoardNo());
			pstmt.setString(2, attach.getOriginalFileName());
			pstmt.setString(3, attach.getRenamedFileName());	
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new BoardException("첨부파일 등록 오류", e);
		}finally {
			close(pstmt);
		}
		return result;
	}
}
