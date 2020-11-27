package top.jisy.docs;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.jisy.docs.service.CollaboratorService;
import top.jisy.docs.service.UserService;

@SpringBootTest
class DocsApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(DocsApplicationTests.class);

    @Autowired
    CollaboratorService collaboratorService;

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        // UserService.queryUser
        log.info("Test");
    }

    @Test
    void TestgetCollaboratorsForDoc() {
        // Doc doc = new Doc();
        // doc.setId(100);
        // doc.setName("233");

    }


}
