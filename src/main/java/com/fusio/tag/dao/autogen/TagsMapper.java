package com.fusio.tag.dao.autogen;

import com.fusio.tag.model.autogen.Tags;
import com.fusio.tag.model.autogen.TagsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagsMapper {
    int countByExample(TagsExample example);

    int deleteByExample(TagsExample example);

    int deleteByPrimaryKey(String tagId);

    int insert(Tags record);

    int insertSelective(Tags record);

    List<Tags> selectByExample(TagsExample example);

    Tags selectByPrimaryKey(String tagId);

    int updateByExampleSelective(@Param("record") Tags record, @Param("example") TagsExample example);

    int updateByExample(@Param("record") Tags record, @Param("example") TagsExample example);

    int updateByPrimaryKeySelective(Tags record);

    int updateByPrimaryKey(Tags record);
}