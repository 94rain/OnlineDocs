package top.jisy.docs.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.jisy.docs.dao.DocMapper;
import top.jisy.docs.pojo.Doc;
import top.jisy.docs.service.DocService;

import java.util.List;

@Component
public class DocServiceImpl implements DocService {
    private static Logger logger = LoggerFactory.getLogger(DocServiceImpl.class);

    @Autowired
    DocMapper docMapper;

    @Override
    public List<Doc> getDocs() {
        return docMapper.queryDoc();
    }

    @Override
    public List<Doc> getDocsByUserId(int userId) {
        return docMapper.queryDocsByUserId(userId);
    }
}
