package member.model.dao;

import static common.JdbcTemplate.close;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import member.model.dto.Member;
import member.model.dto.MemberRole;

public class MemberDao {
	private Properties prop = new Properties();
	
	public MemberDao() {
		// buildpath의 sql/member-query.properties 파일의 내용 불러오기
		String fileName = MemberDao.class.getResource("/sql/member-query.properties").getPath();
		System.out.println("filename@MemberDao = " + fileName);
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 3. 자원 반납(rset, pstmt)
			close(rset);
			close(pstmt);
		}
		return member;
	}
}