package com.fusio.tag.dao.autogen;

import com.fusio.tag.model.autogen.TgTag;
import com.fusio.tag.model.autogen.TgTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TgTagMapper {
    int countByExample(TgTagExample example);

    int deleteByExample(TgTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TgTag record);

    int insertSelective(TgTag record);

    List<TgTag> selectByExample(TgTagExample example);

    TgTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TgTag record, @Param("example") TgTagExample example);

    int updateByExample(@Param("record") TgTag record, @Param("example") TgTagExample example);

    int updateByPrimaryKeySelective(TgTag record);

    int updateByPrimaryKey(TgTag record);
}