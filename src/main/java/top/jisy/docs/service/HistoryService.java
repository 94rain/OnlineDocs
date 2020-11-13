package top.jisy.docs.service;

import top.jisy.docs.pojo.History;

import java.util.List;

public interface HistoryService {

    List<History> getHistoryByDocId(int docId);
}
