package com.fusio.tag.service;

import com.fusio.tag.vo.AllGraph;
import com.fusio.tag.vo.CatgGraph;

public interface CatgServiceI {
	// 查询全graph图
	CatgGraph qryCatgGraph();
	
	// 查询Category和tags整合的层次结构
	AllGraph qryFullGraph();
}