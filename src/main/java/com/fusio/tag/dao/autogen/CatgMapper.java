package com.fusio.tag.dao.autogen;

import com.fusio.tag.model.autogen.Catg;
import com.fusio.tag.model.autogen.CatgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CatgMapper {
    int countByExample(CatgExample example);

    int deleteByExample(CatgExample example);

    int deleteByPrimaryKey(String catgId);

    int insert(Catg record);

    int insertSelective(Catg record);

    List<Catg> selectByExample(CatgExample example);

    Catg selectByPrimaryKey(String catgId);

    int updateByExampleSelective(@Param("record") Catg record, @Param("example") CatgExample example);

    int updateByExample(@Param("record") Catg record, @Param("example") CatgExample example);

    int updateByPrimaryKeySelective(Catg record);

    int updateByPrimaryKey(Catg record);
}