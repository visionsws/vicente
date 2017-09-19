package com.fusio.tag.dao.autogen;

import com.fusio.tag.model.autogen.TgCatg;
import com.fusio.tag.model.autogen.TgCatgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TgCatgMapper {
    int countByExample(TgCatgExample example);

    int deleteByExample(TgCatgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TgCatg record);

    int insertSelective(TgCatg record);

    List<TgCatg> selectByExample(TgCatgExample example);

    TgCatg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TgCatg record, @Param("example") TgCatgExample example);

    int updateByExample(@Param("record") TgCatg record, @Param("example") TgCatgExample example);

    int updateByPrimaryKeySelective(TgCatg record);

    int updateByPrimaryKey(TgCatg record);
}