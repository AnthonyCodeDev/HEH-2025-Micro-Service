package be.heh.reviewservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
    "spring.r2dbc.url=r2dbc:h2:mem:///testdb;MODE=MYSQL",
    "spring.r2dbc.username=sa",
    "spring.r2dbc.password=",
    "spring.sql.init.mode=never"
})
class ReviewServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
