package com.shopme.admin.dashboard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.Rollback;


@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class DashboardTests {
	@Autowired
	private DashboardService dbservice;
	
	@Test
	public void testDashboardService() {
		DashboardInfo summary = dbservice.loadSummary();

		System.out.print(summary);
	}
}
