package com.kh.student.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.student.model.dto.Student;

public class StudentDaoImpl implements StudentDao {

	@Override
	public Student selectOne(SqlSession sqlSession, int no) {
		// statment:String - (mapper의 namespace값).(쿼리태그의 id값)
		return sqlSession.selectOne("student.selectOne", no);
	}

	@Override
	public int insertStuent(SqlSession sqlSession, Student student) {
		 
		return sqlSession.insert("student.insertStudent", student);
	}

	@Override
	public int insertStuent(SqlSession sqlSession, Map<String, Object> studentMap) {
		 
		return sqlSession.insert("student.insertStudentMap", studentMap);
	}

	@Override
	public int getTotalCount(SqlSession sqlSession) {
	 
		return sqlSession.selectOne("student.getTotalCount");
	}

	@Override
	public Map<String, Object> selectOneMap(SqlSession sqlSession, int no) {
		return sqlSession.selectOne("student.selectOneMap", no);
		
	}

	@Override
	public int updateStudent(SqlSession sqlSession, Student student) {
		return sqlSession.update("student.updateStudent", student);
	}

	@Override
	public int deleteStudent(SqlSession sqlSession, int no) {
		return sqlSession.delete("student.deleteStudent", no);
	}

}
