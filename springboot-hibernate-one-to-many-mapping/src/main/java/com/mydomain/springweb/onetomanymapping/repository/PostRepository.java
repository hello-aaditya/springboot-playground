package com.mydomain.springweb.onetomanymapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mydomain.springweb.onetomanymapping.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
