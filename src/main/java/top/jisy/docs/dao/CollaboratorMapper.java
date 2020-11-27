package top.jisy.docs.dao;

import org.apache.ibatis.annotations.Param;
import top.jisy.docs.pojo.Collaborator;

import java.util.List;

public interface CollaboratorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collaborator record);

    int insertSelective(Collaborator record);

    Collaborator selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collaborator record);

    int updateByPrimaryKey(Collaborator record);

    //@Select("SELECT c FROM Collaborator c WHERE c.doc.id=#{id} AND c.hasAccess=#{hasAccess}")
    List<Collaborator> getCollaboratorsForDoc(@Param("id") Integer id, @Param("hasAccess") String hasAccess);
}