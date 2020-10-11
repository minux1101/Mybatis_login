package com.kopo.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kopo.dao.MemberDAO;
import com.kopo.dto.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;

	@Override
	public int insert(MemberVO memberVO) throws Exception {

		return dao.insert(memberVO);
	}

	@Override
	public MemberVO selectOne(String memberId) throws Exception {

		return dao.selectOne(memberId);
	}

	@Override
	public List<MemberVO> selectMember() throws Exception {

		return dao.selectMember();
	}

}
