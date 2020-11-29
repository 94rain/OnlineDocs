package top.jisy.docs.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.jisy.docs.pojo.Doc;

import java.util.List;

public interface DocMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Doc record);

    int insertSelective(Doc record);

    Doc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Doc record);

    int updateByPrimaryKeyWithBLOBs(Doc record);

    int updateByPrimaryKey(Doc record);

    @Select("select * from doc")
    List<Doc> queryDoc();

    @Select("select * from doc where cuser = #{UserId}")
    List<Doc> queryDocsByUserId(@Param("UserId") int UserId);

}