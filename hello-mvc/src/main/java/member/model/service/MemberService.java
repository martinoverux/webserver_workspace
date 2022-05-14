package member.model.service;

import static common.JdbcTemplate.*;
import java.sql.Connection;

import member.model.dao.MemberDao;
import member.model.dto.Member;

/**
 * 
 * 1. connection 생성
 * 2. dao 요청(connection)
 * 3. dml 경우 transaction 처리
 * 4. connection 반환
 * 5. controller로 값 반환 처리
 *
 */
public class MemberService {
	private MemberDao memberDao = new MemberDao();
	
	public Member findByMemberId(String memberId) {
		Connection conn = getConnection();
		Member member = memberDao.findByMemberId(conn, memberId);
		close(conn);
		
		return member;
	}


}