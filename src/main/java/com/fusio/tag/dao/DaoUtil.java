package com.fusio.tag.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.fusio.tag.dao.autogen.CatgMapper;
import com.fusio.tag.dao.autogen.TTestMapper;
import com.fusio.tag.dao.autogen.TagGroupJhMapper;
import com.fusio.tag.dao.autogen.TagJhMapper;
import com.fusio.tag.dao.autogen.TagsMapper;
import com.fusio.tag.dao.autogen.TgCatgMapper;
import com.fusio.tag.dao.autogen.TgTagMapper;
import com.fusio.tag.dao.custom.ExtTagsMapper;

@Repository
public class DaoUtil {
	// 以前的做法: 新添加一个mapper就往这里"注册", 可能会导致spring-test加载过多的对象导致慢
	@Resource
	public TTestMapper tTestMapper;
	@Resource
	public TgTagMapper tgTagMapper;
	@Resource
	public TgCatgMapper tgCatgMapper;
	
	@Resource
	public TagsMapper tagsMapper;
	@Resource
	public CatgMapper catgMapper;
	
	@Resource
	public TagGroupJhMapper tagGroupJhMapper;
	@Resource
	public TagJhMapper tagJhMapper;
	
	
	
	
	@Resource
	public ExtTagsMapper extTagsMapper;
	// 旧的方式----------------------------------

	// 已测试过sqlSession配置为scope="prototype"并不会对事务造成影响
	@Resource
	private SqlSession sqlSession;

	// 获取通过这个类动态获取,比写上n多的mapper,在spring-test的时候会加载快点(不需要启动的时候全部创建)
	public <T> T getMapper(Class<T> mapper) {
		return sqlSession.getMapper(mapper);
	}
}
