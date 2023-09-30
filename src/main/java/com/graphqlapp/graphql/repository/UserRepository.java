package com.graphqlapp.graphql.repository;

import com.graphqlapp.graphql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
