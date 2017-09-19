package com.fusio.tag.dao.autogen;

import com.fusio.tag.model.autogen.TagGroupJh;
import com.fusio.tag.model.autogen.TagGroupJhExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagGroupJhMapper {
    int countByExample(TagGroupJhExample example);

    int deleteByExample(TagGroupJhExample example);

    int deleteByPrimaryKey(String tagGroupId);

    int insert(TagGroupJh record);

    int insertSelective(TagGroupJh record);

    List<TagGroupJh> selectByExample(TagGroupJhExample example);

    TagGroupJh selectByPrimaryKey(String tagGroupId);

    int updateByExampleSelective(@Param("record") TagGroupJh record, @Param("example") TagGroupJhExample example);

    int updateByExample(@Param("record") TagGroupJh record, @Param("example") TagGroupJhExample example);

    int updateByPrimaryKeySelective(TagGroupJh record);

    int updateByPrimaryKey(TagGroupJh record);
}