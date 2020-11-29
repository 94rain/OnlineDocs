package top.jisy.docs.service;

import org.springframework.stereotype.Component;
import top.jisy.docs.pojo.Doc;

import java.util.List;

@Component
public interface DocService {

    List<Doc> getDocs();

    List<Doc> getDocsByUserId(int userId);

    String createDoc(int userId, String documentName);
}
