package com.aptech.blogapi;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.aptech.blogapi.model.Blog;
import com.aptech.blogapi.repository.BlogRepository;

@SpringBootApplication

public class BlogapiApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(BlogapiApplication.class, args);
		BlogRepository blogRepositoy = context.getBean(BlogRepository.class);

		Random random = new Random();
		for (int i = 1; i <= 1000; i++) {

			Blog blog = new Blog();
			blog.setBlogId((i))
					.setTitle("Title " + i)
					.setRating(random.ints(1, 5).findFirst().getAsInt())
					.setUrl("http:localhost:8080/blog/" + i);
			blogRepositoy.save(blog);
		}

	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000")
						.allowedMethods("GET", "POST", "DELETE", "PUT");
			}
		};
	}

}
