package top.jisy.docs.dao;

import top.jisy.docs.pojo.Collaborator;

public interface CollaboratorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collaborator record);

    int insertSelective(Collaborator record);

    Collaborator selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collaborator record);

    int updateByPrimaryKey(Collaborator record);
}