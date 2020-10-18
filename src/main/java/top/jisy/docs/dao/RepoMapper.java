package top.jisy.docs.dao;

import top.jisy.docs.pojo.Repo;

public interface RepoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Repo record);

    int insertSelective(Repo record);

    Repo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Repo record);

    int updateByPrimaryKey(Repo record);
}