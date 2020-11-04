package top.jisy.docs.service;

import top.jisy.docs.pojo.Doc;

import java.util.List;

public interface DocService {

    List<Doc> getDocs();

    List<Doc> getDocsByUserId(int userId);
}
