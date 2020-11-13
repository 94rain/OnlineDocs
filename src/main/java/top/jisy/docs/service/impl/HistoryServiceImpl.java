package top.jisy.docs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.jisy.docs.pojo.History;
import top.jisy.docs.service.HistoryService;

import java.util.List;

public class HistoryServiceImpl implements HistoryService {

    @Autowired

    @Override
    public List<History> getHistoryByDocId(int docId) {
        return null;
    }
}
