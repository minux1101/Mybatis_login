package com.kopo.service;

import java.util.List;

import com.kopo.dto.MemberVO;

public interface MemberService {

	public int insert(MemberVO memberVO) throws Exception;

	public MemberVO selectOne(String memberId) throws Exception;

	public List<MemberVO> selectMember() throws Exception;
}
