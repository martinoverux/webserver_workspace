package member.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import board.model.dto.Attachment;
import board.model.dto.Board;
import board.model.exception.BoardException;
import common.HelloMvcUtils;
import common.JdbcTemplate;
import member.model.dto.Member;
import member.model.dto.MemberRole;
import member.model.exception.MemberException;

public class MemberDao {
	private Properties prop = new Properties();
	
	public MemberDao() {
		// buildpath의 sql/member-query.properties 파일의 내용 불러오기
		String fileName = MemberDao.class.getResource("/sql/member-query.properties").getPath();
//		System.out.println("filename@MemberDao = " + fileName);
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member findByMemberId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findByMemberId");
		Member member = null;
		
		try {
			// 1. pstmt 객체 & 미완성 쿼리 값 대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			// 2. 실행 및 rset 처리
			rset = pstmt.executeQuery();
			while(rset.next()) {
				member = handleMemberResultSet(rset);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 3. 자원 반납(rset, pstmt)
			close(rset);
			close(pstmt);
		}
		return member;
	}

	private Member handleMemberResultSet(ResultSet rset) throws SQLException {
		Member member;
		member = new Member();
		member.setMemberId(rset.getString("member_id"));
		member.setPassword(rset.getString("password"));
		member.setMemberName(rset.getString("member_name"));
		// "U" -> MemberRole.U, "A" -> MemberRole.A 변환
		member.setMemberRole(MemberRole.valueOf(rset.getString("member_role")));
		member.setGender(rset.getString("gender"));
		member.setBirthday(rset.getDate("birthday"));
		member.setEmail(rset.getString("email"));
		member.setPhone(rset.getString("phone"));
		member.setAddress(rset.getString("address"));
		member.setHobby(rset.getString("hobby"));
		member.setEnrollDate(rset.getDate("enroll_date"));
		return member;
	}
	
	private Board handleBoardResultSet(ResultSet rset) throws SQLException {
		Board board;
		board = new Board();

		board.setNo(rset.getInt("no"));
		board.setTitle(rset.getString("title"));
		board.setMemberId(rset.getString("member_id"));
		board.setContent(rset.getString("content"));
		board.setReadCount(rset.getInt("read_Count"));
		board.setRegDate(rset.getDate("reg_date"));
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
	
	

	public int insertMember(Connection conn, Member member) {
		String sql = prop.getProperty("insertMember");
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, String.valueOf(member.getMemberRole()));
			pstmt.setString(5, member.getGender());
			pstmt.setDate(6, member.getBirthday());
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getPhone());
			pstmt.setString(9, member.getAddress());
			pstmt.setString(10, member.getHobby());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new MemberException("회원가입 오류", e);
		}finally {
			close(pstmt);
		}
		return result;
	}

	/**
	 * updateMember = update member 
	 * set member_name = ?, gender = ?,  birthday = ?, email = ?, phone = ?, address = ?, hobby = ? 
	 * where member_id = ?
	 * 
	 */
	public int updateMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateMember");
		try {
			// 1. pstmt객체 생성 & 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2, member.getGender());
			pstmt.setDate(3, member.getBirthday());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getHobby());
			pstmt.setString(8, member.getMemberId());

			// 2. 실행
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new MemberException("회원정보수정 오류", e);
		} finally {
			// 3. 자원반납 - pstmt
			close(pstmt);
		}
		return result;
	}

	public int deleteMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new MemberException("회원 탈퇴 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updatePassword(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePassword");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new MemberException("비밀번호 수정 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	/**
	 * 1건 조회 시 member 객체 하나 또는 null 리턴
	 * n건 조회 시 여러 건의 member 객체를 가진 list 또는 빈 list 
	 * @param param 
	 * 
	 */
	public List<Member> findAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> list = new ArrayList<>();
		String sql = prop.getProperty("findAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member member = handleMemberResultSet(rset);
				list.add(member);
			}
		} catch (Exception e) {
			throw new MemberException("관리자 회원목록 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int updateMemberRole(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMemberRole");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, String.valueOf(member.getMemberRole()));
			pstmt.setString(2, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new MemberException("회원 권한 변경 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Member> findBy(Connection conn, Map<String, String> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> list = new ArrayList<>();
		String sql = prop.getProperty("findBy");
		sql = sql.replace("#", param.get("searchType"));
		System.out.println("sql = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + param.get("searchKeyword") + "%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member member = handleMemberResultSet(rset);
				list.add(member);
			}
		} catch (Exception e) {
			throw new MemberException("관리자 회원검색 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
//	public static void main(String[] args) {
//		new MemberDao().updatePasswordAll();
//	}
	
    public void updatePasswordAll() { 
    	// 1. 회원 아이디 조회 / 신규 비밀번호 설정
        Connection conn = JdbcTemplate.getConnection();
        String sql = prop.getProperty("findAll");
        List<Member> list = new ArrayList<>();
        try(
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rset = pstmt.executeQuery();
        ){
            while(rset.next()) {
                String memberId = rset.getString("member_id");
                Member member = new Member();
                member.setMemberId(memberId);
                member.setPassword(HelloMvcUtils.encrypt("1234", memberId));
                list.add(member);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list); 
        
        // 비밀번호 업데이트
        sql = prop.getProperty("updatePassword");
        try(
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            for(Member member : list) {
                pstmt.setString(1, member.getPassword());
                pstmt.setString(2, member.getMemberId());
                pstmt.executeUpdate();
                System.out.println("변경완료 : " + member.getMemberId() + " - " + member.getPassword());
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

	public int getTotalContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int getTotalContents = 0;
		String sql = prop.getProperty("getTotalContents");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				getTotalContents = rset.getInt(1); // 컬럼 인덱스
			}
		} catch (Exception e) {
			throw new MemberException("전체 회원수 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return getTotalContents;
	}

	public List<Board> findAllBoard(Connection conn, Map<String, Object> paramBorad) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> boardList = new ArrayList<>();
		String sql = prop.getProperty("findAllBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) paramBorad.get("start"));
			pstmt.setInt(2, (int) paramBorad.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Board board = handleBoardResultSet(rset);
				boardList.add(board);
			}
		} catch (Exception e) {
			throw new MemberException("게시판 조회 오류", e);
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
			throw new MemberException("전체 게시글 조회 오류", e);
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
			throw new MemberException("첨부파일 포함 게시글 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return attachList;
	}
	
}