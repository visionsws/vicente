package com.fusio.tag.service.cmm;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fusio.tag.service.impl.BaseService;

@Service
@Transactional
public class CommonService extends BaseService<CommonService>implements CommonServiceI {

	@Override
	public Integer getIdByTagCode(String code) {
		return daoUtil.extTagsMapper.getIdByTagCode(code);
	}

	@Override
	public Integer getIdByCatgCode(String code) {
		return daoUtil.extTagsMapper.getIdByCatgCode(code);
	}

	@Override
	public String getTagIdByTagCode(String code) {
		return daoUtil.extTagsMapper.getTagIdByTagCode(code);
	}

	@Override
	public String getCatgIdByCatgCode(String code) {
		return daoUtil.extTagsMapper.getCatgIdByCatgCode(code);
	}

	
}