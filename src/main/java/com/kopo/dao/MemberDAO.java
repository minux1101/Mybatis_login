package com.kopo.dao;

import java.util.List;

import com.kopo.dto.MemberVO;

public interface MemberDAO {
	public int insert(MemberVO memberVo) throws Exception;

	public MemberVO selectOne(String memberId) throws Exception;

	public List<MemberVO> selectMember() throws Exception;
}
