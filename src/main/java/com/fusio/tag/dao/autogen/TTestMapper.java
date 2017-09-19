package com.fusio.tag.dao.autogen;

import com.fusio.tag.model.autogen.TTest;
import com.fusio.tag.model.autogen.TTestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TTestMapper {
    int countByExample(TTestExample example);

    int deleteByExample(TTestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TTest record);

    int insertSelective(TTest record);

    List<TTest> selectByExample(TTestExample example);

    TTest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TTest record, @Param("example") TTestExample example);

    int updateByExample(@Param("record") TTest record, @Param("example") TTestExample example);

    int updateByPrimaryKeySelective(TTest record);

    int updateByPrimaryKey(TTest record);
}