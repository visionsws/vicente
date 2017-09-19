package com.fusio.tag.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fusio.tag.commons.utils.MyBatisParam;
import com.fusio.tag.dao.autogen.TTestMapper;
import com.fusio.tag.dao.custom.ExtTTestMapper;
import com.fusio.tag.model.autogen.TTest;
import com.fusio.tag.service.TTestServiceI;

@Service
@Transactional
public class TTestService extends BaseService<TTestService>implements TTestServiceI {

	@Override
	public void insert(TTest tTest) {
		TTestMapper mapper = daoUtil.getMapper(TTestMapper.class);
		mapper.insertSelective(tTest);
	}

	@Override
	public List custom() {
		ExtTTestMapper mapper = daoUtil.getMapper(ExtTTestMapper.class);
//		return mapper.custom(MyBatisParam.create().get());
//		return mapper.test_json1(MyBatisParam.create().get());
		return mapper.test_json2(MyBatisParam.create().get());
		
		
	}

	
}