package com.mydomain.springweb.onetoonemapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mydomain.springweb.onetoonemapping.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
