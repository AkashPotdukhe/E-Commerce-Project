package com.csi;

import com.csi.utils.entities.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.csi.services.AdminService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaAuditing

public class EcommerceBackendApplication {

	private static final Logger log = LoggerFactory.getLogger(EcommerceBackendApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AdminService srv) {
	    return (args) -> {
	    	if(srv.countAdmin()==0) {
	    		Admin admin=new Admin();
	    		admin.setUserid("admin");
	    		admin.setPwd("admin");
	    		admin.setUname("Administrator");
	    		srv.updateAdmin(admin);
	    		log.info("Admin user created successfully");
	    	}
	    };
	}
}
