package org.itstep.msk.app.repotests;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public  abstract class TestContainerBase {

    @Container
    private static MySQLContainer database = (MySQLContainer) new MySQLContainer("mysql:5.7.32")
            .withReuse(true);

    static {
        database.start();
    }

    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",database::getJdbcUrl);
        registry.add("spring.datasource.name", database::getUsername);
        registry.add("spring.datasource.password",database::getPassword);
    }

}
