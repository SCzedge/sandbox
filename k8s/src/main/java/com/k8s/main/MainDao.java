package com.k8s.main;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MainDao {
	private final SqlSession sqlSession;

	public MainDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<Map<String, Object>> fetch() {
		return sqlSession.selectList("mapper.db.fetch");
	}

	public int submit(Map<String, Object> data) {
		return sqlSession.insert("mapper.db.submit", data);
	}
}
