package com.slyynewwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class SlyynewwebserviceApplication extends SpringBootServletInitializer {
     
	//20160907
	 @Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(SlyynewwebserviceApplication.class);
	    }
	 //
	 
	 
	public static void main(String[] args) {
		SpringApplication.run(SlyynewwebserviceApplication.class, args);
	}
}
