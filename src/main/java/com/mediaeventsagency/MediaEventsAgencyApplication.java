package com.mediaeventsagency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MediaEventsAgencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediaEventsAgencyApplication.class, args);
	}



}
