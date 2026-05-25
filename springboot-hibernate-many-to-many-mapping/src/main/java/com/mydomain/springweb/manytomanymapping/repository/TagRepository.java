package com.mydomain.springweb.manytomanymapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mydomain.springweb.manytomanymapping.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}