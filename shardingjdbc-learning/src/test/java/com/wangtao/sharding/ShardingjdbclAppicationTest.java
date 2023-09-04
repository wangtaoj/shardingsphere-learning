package com.wangtao.sharding;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author wangtao
 * Created at 2023/9/3 15:18
 */
@SpringBootTest
public class ShardingjdbclAppicationTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void contextLoad() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }
}
