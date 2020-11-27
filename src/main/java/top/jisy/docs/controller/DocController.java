package top.jisy.docs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.jisy.docs.pojo.Doc;
import top.jisy.docs.service.DocService;

import java.util.List;

@RestController
public class DocController {

    @Autowired
    DocService docService;

    @GetMapping("/doc/getAll")
    public List<Doc> getDoc() {
        return docService.getDocs();
    }

    @GetMapping("/doc/get/{UserId}")
    public List<Doc> getDocByUserId(@PathVariable("UserId") int id) {
        return docService.getDocsByUserId(id);
    }


}
