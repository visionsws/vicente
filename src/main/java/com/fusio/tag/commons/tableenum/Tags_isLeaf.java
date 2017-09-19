package com.fusio.tag.commons.tableenum;

import com.fusio.tag.commons.exception.ExceptionUtil;

public enum Tags_isLeaf {
	// 是否叶子节点,0-否,1-是
	NO(0), YES(1);

	public short val;

	private Tags_isLeaf(int val) {
		this.val = (short) val;
	}

	public static Tags_isLeaf get(int val) {
		for (Tags_isLeaf s : values())
			if (val == s.val)
				return s;
		throw ExceptionUtil.getInvalidEnumEx();
	}
}
