package top.jisy.docs.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.jisy.docs.dao.mapper.DocMapper;
import top.jisy.docs.dao.mapper.RepoMapper;
import top.jisy.docs.pojo.Doc;
import top.jisy.docs.pojo.Repo;
import top.jisy.docs.service.DocService;

import java.util.List;

@Component
public class DocServiceImpl implements DocService {
    private static Logger logger = LoggerFactory.getLogger(DocServiceImpl.class);

    @Autowired
    DocMapper docMapper;

    @Autowired
    RepoMapper repoMapper;


    @Override
    public List<Doc> getDocs() {
        return docMapper.queryDoc();
    }

    @Override
    public List<Doc> getDocsByUserId(int userId) {
        return docMapper.queryDocsByUserId(userId);
    }

    @Override
    public String createDoc(int userId, String documentName) {
        Repo repository = repoMapper.getRepoByUser(userId);

        Doc document = new Doc();
        document.setCuser(userId);
        document.setUuser(userId);
        document.setFkRepo(repository.getId());
        document.setName(documentName.trim());

        docMapper.insertSelective(document);

        return null;
    }

}
