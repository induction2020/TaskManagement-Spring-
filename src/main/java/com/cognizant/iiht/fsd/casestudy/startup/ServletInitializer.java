package com.cognizant.iiht.fsd.casestudy.startup;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.cognizant.iiht.fsd.casestudy.TaskManagementApplication;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TaskManagementApplication.class);
	}

}
