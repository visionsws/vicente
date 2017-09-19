package com.fusio.tag.dao.autogen;

import com.fusio.tag.model.autogen.TagJh;
import com.fusio.tag.model.autogen.TagJhExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagJhMapper {
    int countByExample(TagJhExample example);

    int deleteByExample(TagJhExample example);

    int deleteByPrimaryKey(String tagId);

    int insert(TagJh record);

    int insertSelective(TagJh record);

    List<TagJh> selectByExample(TagJhExample example);

    TagJh selectByPrimaryKey(String tagId);

    int updateByExampleSelective(@Param("record") TagJh record, @Param("example") TagJhExample example);

    int updateByExample(@Param("record") TagJh record, @Param("example") TagJhExample example);

    int updateByPrimaryKeySelective(TagJh record);

    int updateByPrimaryKey(TagJh record);
}