package com.mydomain.springweb.manytomanymapping;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mydomain.springweb.manytomanymapping.entity.Post;
import com.mydomain.springweb.manytomanymapping.entity.Tag;
import com.mydomain.springweb.manytomanymapping.repository.PostRepository;

@SpringBootApplication
public class SpringbootHibernateManyToManyMappingApplication implements CommandLineRunner {

	// CONSTRUCTOR DI
	private final PostRepository postRepository;
	public SpringbootHibernateManyToManyMappingApplication(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootHibernateManyToManyMappingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Post post = new Post("Hibernate Many to Many Example",
				"Description", "content");
		
		Tag springBoot = new Tag("Spring Boot");
		Tag hibernate = new Tag("Hibernate");
		
		// ADD tag REFERENCES post
		post.getTags().add(springBoot);
		post.getTags().add(hibernate);
		
		// ADD post REFERENCES tag 
		springBoot.getPosts().add(post);
		hibernate.getPosts().add(post);
		
		postRepository.save(post);
	}

}
