package demo.main;

import java.io.UnsupportedEncodingException;

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

		/*
		 * String string = "您的注册验证码为：306706，10分钟内有效，切勿将验证码泄露于他人。";
		 * 
		 * try { byte[] string1 = string.getBytes("gbk"); System.out.println(string1);
		 * System.out.println(new String(string1, "gbk")); } catch
		 * (UnsupportedEncodingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

	}

}
