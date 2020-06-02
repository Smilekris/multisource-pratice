package com.example.multisource;

import com.example.multisource.config.Db1Config;
import com.example.multisource.config.Db2Config;
import com.example.multisource.util.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(value ="com.example.multisource.*")
@Import(value = {Db1Config.class, Db2Config.class})
public class PracticeApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(PracticeApplication.class, args);
		SpringContextUtil.setApplicationContext(run);
	}

}
