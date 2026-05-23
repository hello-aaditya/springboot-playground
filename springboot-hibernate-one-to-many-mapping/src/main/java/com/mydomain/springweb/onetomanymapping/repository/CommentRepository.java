package com.mydomain.springweb.onetomanymapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mydomain.springweb.onetomanymapping.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
