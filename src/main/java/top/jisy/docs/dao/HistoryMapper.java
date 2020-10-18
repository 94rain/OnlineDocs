package top.jisy.docs.dao;

import top.jisy.docs.pojo.History;

public interface HistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(History record);

    int insertSelective(History record);

    History selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(History record);

    int updateByPrimaryKeyWithBLOBs(History record);

    int updateByPrimaryKey(History record);
}