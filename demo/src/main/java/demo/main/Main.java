package demo.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@MapperScan("demo.dao")
@ComponentScan(basePackages = { "demo.*" })
@EnableSwagger2
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
