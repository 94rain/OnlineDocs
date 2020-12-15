package top.jisy.docs;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.jisy.docs.enums.StatusEnum;
@SpringBootTest
public class StatusTest {
    @Test
    public void sTest0(){
        System.out.println(StatusEnum.FAIL);
    }
}
