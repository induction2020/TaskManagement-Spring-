package com.cognizant.iiht.fsd.casestudy;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskManagementApplicationTests {

	@Test
	public void contextLoads() {
	}
	

	@Autowired
    private DataSource dataSource;
 
    @Test
    public void hikariConnectionPoolIsConfigured() throws SQLException {
        assertEquals("com.zaxxer.hikari.HikariDataSource", dataSource.getClass().getName());
        
    }

}
