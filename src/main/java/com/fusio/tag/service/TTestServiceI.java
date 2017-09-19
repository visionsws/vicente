package com.fusio.tag.service;

import java.util.List;

import com.fusio.tag.model.autogen.TTest;

public interface TTestServiceI {
	void insert(TTest tTest);

	List custom();
}