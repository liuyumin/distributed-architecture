package distributed.architecture.server;

import distributed.architecture.server.apollo.TestJavaConfigBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistributedArchitectureServerApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Bean
    public TestJavaConfigBean javaConfigBean() {
        TestJavaConfigBean testJavaConfigBean = new TestJavaConfigBean();
        System.out.println("aaaaaaa=="+testJavaConfigBean.name);
        return testJavaConfigBean;
    }


}
