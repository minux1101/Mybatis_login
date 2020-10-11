package com.kopo.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kopo.dto.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Inject
	private SqlSession sqlSession;
	private static final String Namespace = "com.kopo.mapper.memberMapper";

	@Override
	public int insert(MemberVO memberVO) throws Exception {
		return sqlSession.insert(Namespace + ".insert", memberVO);
	}

	@Override
	public MemberVO selectOne(String memberId) throws Exception {
		return sqlSession.selectOne(Namespace + ".selectOne", memberId);
	}

	@Override
	public List<MemberVO> selectMember() throws Exception {
		return sqlSession.selectList(Namespace + ".select");
	}
}
