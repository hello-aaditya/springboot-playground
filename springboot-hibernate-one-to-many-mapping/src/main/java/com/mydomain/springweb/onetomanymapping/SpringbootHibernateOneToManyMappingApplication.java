package com.mydomain.springweb.onetomanymapping;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mydomain.springweb.onetomanymapping.entity.Comment;
import com.mydomain.springweb.onetomanymapping.entity.Post;
import com.mydomain.springweb.onetomanymapping.repository.PostRepository;

@SpringBootApplication
public class SpringbootHibernateOneToManyMappingApplication implements CommandLineRunner {
	
	// CONSTRUCTOR DI
	private final PostRepository postRepository;
	
	public SpringbootHibernateOneToManyMappingApplication(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootHibernateOneToManyMappingApplication.class, args);
	}
	
	
	
	@Override
	public void run(String... args) throws Exception {
		
		Post post = new Post("Learn One-To-Many Mapping using JPA and Hibernate","Description: Learn One-To_Many Mapping using JPA and Hibernate");
		
		Comment comment1 = new Comment("Mapping topic is interesting.");
		Comment comment2 = new Comment("Unidirectional Mapping.");
		Comment comment3 = new Comment("Important Topic.");
		
		post.getComments().add(comment1);
		post.getComments().add(comment2);
		post.getComments().add(comment3);
		
		postRepository.save(post);
	}
	
	
}
