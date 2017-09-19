package com.fusio.tag.dao.autogen;

import com.fusio.tag.model.autogen.TTestJson;
import com.fusio.tag.model.autogen.TTestJsonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TTestJsonMapper {
    int countByExample(TTestJsonExample example);

    int deleteByExample(TTestJsonExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(TTestJson record);

    int insertSelective(TTestJson record);

    List<TTestJson> selectByExample(TTestJsonExample example);

    TTestJson selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") TTestJson record, @Param("example") TTestJsonExample example);

    int updateByExample(@Param("record") TTestJson record, @Param("example") TTestJsonExample example);

    int updateByPrimaryKeySelective(TTestJson record);

    int updateByPrimaryKey(TTestJson record);
}