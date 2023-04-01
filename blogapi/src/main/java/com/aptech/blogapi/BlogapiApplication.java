package com.aptech.blogapi;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
